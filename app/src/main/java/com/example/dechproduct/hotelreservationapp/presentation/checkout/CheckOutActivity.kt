package com.example.dechproduct.hotelreservationapp.presentation.checkout

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.R
import com.example.dechproduct.hotelreservationapp.databinding.ActivityCheckOutBinding
import com.example.dechproduct.hotelreservationapp.presentation.checkout.adapter.CheckOutAdapter
import com.example.dechproduct.hotelreservationapp.presentation.menu.MenuActivity
import com.example.dechproduct.hotelreservationapp.presentation.reservation.add.AddReservationActivity
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.example.dechproduct.hotelreservationapp.util.swipe.Helper.MySwipeHelper
import com.example.dechproduct.hotelreservationapp.util.swipe.listener.MyButton
import com.example.dechproduct.hotelreservationapp.util.swipe.listener.MyButtonClickListener
import com.example.dechproduct.hotelreservationapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CheckOutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckOutBinding
    private val checkOutViewModel: CheckOutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckOutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackMenu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        var searchBar = binding.searchBar
        searchBar.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query == "")

                    checkOutViewModel.populateReserve()
                else

                    checkOutViewModel.searchReserve(query.capitalize())

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText == "")

                    checkOutViewModel.populateReserve()
                else

                    checkOutViewModel.searchReserve(newText.capitalize())

                return false
            }
        })

        binding.reservationList.layoutManager = LinearLayoutManager(this)

        checkOutViewModel.populateReserve()

        onSwipeHandle()
        observeSearch()
        observeCheckOutResolve()

    }

    private fun onSwipeHandle() {
        val swipe = object : MySwipeHelper(this, binding.reservationList, 200) {
            override fun instantiateMyButton(
                viewHolder: RecyclerView.ViewHolder,
                buffer: MutableList<MyButton>
            ) {
                buffer.add(
                    MyButton(this@CheckOutActivity,
                        "Check-Out",
                        45,
                        R.drawable.ic_baseline_beenhere_24,
                        Color.parseColor("#FF5003"),
                        object : MyButtonClickListener {
                            override fun onClick(pos: Int) {
                                checkOutViewModel.isExtend = false
                                checkOutViewModel.checkOutReserved(checkOutViewModel.result[pos])
                            }
                        }
                    )
                )
                buffer.add(
                    MyButton(this@CheckOutActivity,
                        "ExtendStay",
                        45,
                        R.drawable.ic_baseline_king_bed_24,
                        Color.parseColor("#F8D568"),
                        object : MyButtonClickListener {
                            override fun onClick(pos: Int) {
                                checkOutViewModel.isExtend = true
                                checkOutViewModel.checkOutReserved(checkOutViewModel.result[pos])
                                val intent =
                                    Intent(applicationContext, AddReservationActivity::class.java)
                                intent.putExtra(
                                    Constants.INTENT_SELECTED_BOOKING,
                                    checkOutViewModel.result[pos]
                                )
                                startActivityForResult(intent, 0)
                            }
                        }
                    )
                )
            }

        }
    }

    private fun observeSearch() {
        checkOutViewModel.reserver.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { reservationList ->
                        Log.d("CheckOutResActivity", reservationList.toString())
                        checkOutViewModel.result = reservationList
                        binding.reservationList.adapter =
                            CheckOutAdapter(checkOutViewModel.result)            //here adapter set up recycler view
                    }
                }

                is Resource.Failure -> {
                    Toast.makeText(applicationContext, it.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    private fun observeCheckOutResolve() {
        checkOutViewModel.resolveReservation.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { reservation ->
                        Log.d("CheckOutResActivity", reservation.toString())
                        Toast.makeText(
                            applicationContext,
                            "Check-Out Successful!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        if (!checkOutViewModel.isExtend) {
                            val intent =
                                Intent(this@CheckOutActivity, MenuActivity::class.java)
                            startActivity(intent)
                        }
                        else{
                            finish()
                        }
                    }
                }
                is Resource.Failure -> {
                    Toast.makeText(applicationContext, it.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}