package com.example.dechproduct.hotelreservationapp.presentation.checkinDetail

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.dechproduct.hotelreservationapp.databinding.ActivityCheckinDetailBinding
import com.example.dechproduct.hotelreservationapp.presentation.menu.MenuActivity
import com.example.dechproduct.hotelreservationapp.presentation.reservation.ReservationMenuActivity
import com.example.dechproduct.hotelreservationapp.presentation.roomAvailableBottomSheet.RoomAvailableBottomSheetFragment
import com.example.dechproduct.hotelreservationapp.presentation.roomBedBottomSheet.adapter.adapter.RoomBedBottomSheetFragment
import com.example.dechproduct.hotelreservationapp.presentation.roomTypeBottomSheet.RoomTypeBottomSheetFragment
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.example.dechproduct.hotelreservationapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CheckinDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckinDetailBinding    // <- can click here to open the xml that related
    private val checkInDetailViewModel: CheckinDetailViewModel by viewModels()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    var bottomSheetChangeRoomTypeFragment = RoomTypeBottomSheetFragment()

    var bottomSheetCheckingRoomAvailableFragment = RoomAvailableBottomSheetFragment()

    var bottomSheetRoomBedFragment = RoomBedBottomSheetFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckinDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // you can use "binding.VIEWNAME" to represent findViewByID
        // ex)      val displayName = findViewById<TextView>(R.id.textView5)
        // with ->  val displayName = binding.textView5     that's all! (no need <Type of view> and findVeiwByID) -> read viewbinding library


        binding.btnBackMenu.setOnClickListener {
            val intent = Intent(this, ReservationMenuActivity::class.java)
            finish()
        }

        binding.tvEditRoomType.setOnClickListener {
            bottomSheetChangeRoomTypeFragment.show(supportFragmentManager, "TAG")
        }

//        binding.roomType.text = "Suite"
//
//
//        binding.tvCheckInDate.text = "11-03-2023"
//
//        binding.tvCheckInDate.text = "13-03-2023"


        binding.btnPlus.setOnClickListener {
            lifecycleScope.launch {
                checkInDetailViewModel.increment()
                binding.edtGuestNumber.setText(checkInDetailViewModel.amount.value.toString())

            }

        }

        binding.btnMinus.setOnClickListener {
            lifecycleScope.launch {
                checkInDetailViewModel.decrement()
                binding.edtGuestNumber.setText(checkInDetailViewModel.amount.value.toString())
            }

        }

        binding.btnPlusChild.setOnClickListener {
            lifecycleScope.launch {
                checkInDetailViewModel.incrementChild()
                binding.edtChildNumber.setText(checkInDetailViewModel.amountChild.value.toString())
            }
        }

        binding.btnMinusChild.setOnClickListener {
            lifecycleScope.launch {
                checkInDetailViewModel.decrementChild()
                binding.edtChildNumber.setText(checkInDetailViewModel.amountChild.value.toString())
            }
        }


        binding.edtGuestNumber.setOnClickListener {
            Log.i("CheckInDetailActivity", "edittextGuest clicked")
        }


        binding.edtChildNumber.setOnClickListener {
            Log.i("CheckInDetailActivity", "edittextChild clicked")
        }

        binding.tvCheckingRoom.setOnClickListener {
            bottomSheetCheckingRoomAvailableFragment.show(supportFragmentManager, "TAG")
        }


        binding.tvSelectRoomBed.setOnClickListener {
            bottomSheetRoomBedFragment.show(supportFragmentManager, "TAG")
        }

        binding.btnConfirmationCheckIn.setOnClickListener {
            Toast.makeText(applicationContext, "Confirmation Check in clicked", Toast.LENGTH_LONG)
                .show()
            lifecycleScope.launch { checkInDetailViewModel.checkInReserved() }
        }

        binding.cbBreakfast.setOnClickListener {
            Toast.makeText(applicationContext, "checkbox breakfast  clicked", Toast.LENGTH_LONG)
                .show()

        }

        binding.cbSmoking.setOnClickListener {
            Toast.makeText(applicationContext, "checkbox smoking  clicked", Toast.LENGTH_LONG)
                .show()

        }
        receiveSelected()
        observeUpdateInfo()
        observeCheckInResolve()
    }

    private fun receiveSelected() {
        sharedPreferences = applicationContext.getSharedPreferences(
            Constants.SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )
//        Log.d("HERH:", sharedPreferences.getString(
//            Constants.RESERVED_ID,
//            null).toString()
//        )

        lifecycleScope.launch {
            checkInDetailViewModel.updateInfo(
                sharedPreferences.getString(
                    Constants.RESERVED_ID,
                    null
                ).toString()
            )
        }
    }

    private fun observeUpdateInfo() {
        checkInDetailViewModel.selected.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { reservation ->
                        Log.d("CheckInResActivity", reservation.toString())
//                        Toast.makeText(
//                            applicationContext,
//                            reservation.toString(),
//                            Toast.LENGTH_SHORT
//                        ).show()
                        checkInDetailViewModel.reservation = reservation

                        binding.roomType.text = reservation.guestRoom?.roomType
                        binding.tvCheckInDate.text = reservation.arrivalDate
                        binding.tvCheckOutDate.text = reservation.departDate
                        binding.tvDisplayRoomBed.text = reservation.guestRoom?.roomBeds
                        reservation.adultCount?.let { adult_count ->
                            binding.edtGuestNumber.setText(
                                adult_count
                            )
                        }
                        reservation.childCount?.let { child_count ->
                            binding.edtGuestNumber.setText(
                                child_count
                            )
                        }
                        if (reservation.guestRoom?.breakfast == true) {
                            //TODO:Set check box true
                        } else {
                            //Set check box false
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

    private fun observeCheckInResolve() {
        checkInDetailViewModel.selected.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { reservation ->
                        Log.d("CheckInResActivity", reservation.toString())
                        val intent =
                            Intent(this@CheckinDetailActivity, MenuActivity::class.java)
                        startActivity(intent)
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
