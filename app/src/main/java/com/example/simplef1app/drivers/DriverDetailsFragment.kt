package com.example.simplef1app.drivers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.simplef1app.R
import com.example.simplef1app.api.JsonTools
import com.example.simplef1app.data.database.entities.DriverEntity
import com.example.simplef1app.databinding.FragmentDriverDetailsBinding
import java.util.*


class DriverDetailsFragment : Fragment() {
    private var itemJsonString : String? = null
    private lateinit var driver : DriverEntity

    private var _binding: FragmentDriverDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemJsonString = it.getString(DETAIL_JSON_STRING)
            driver = JsonTools.gsonParser!!.fromJson(itemJsonString, DriverEntity::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDriverDetailsBinding.inflate(inflater, container, false)
        val fullName = "${driver.givenName} ${driver.familyName}"

        binding.driverNameDetailsTextView.text = fullName
        binding.driverIdDetailsTextView.text = driver.driverId
        binding.driverNumberDetailsTextView.text = driver.permanentNumber
            ?: getString(R.string.driver_number_not_available)
        binding.driverNationalityDetailsTextView.text = driver.nationality
        binding.driverBirthDetailsTextView.text = driver.dateOfBirth

        Glide.with(requireContext()).load(randomImage()).into(binding.driverPictureDetailsImageView)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun randomImage(): Int {
        val imgList: List<Int> = listOf(
            R.drawable.fernando_alonso,
            R.drawable.lewis_hamilton,
            R.drawable.max_verstappen,
            R.drawable.schumacher,
            R.drawable.sebastian_vettel
        )
        val rand = Random()
        val pickedImg = rand.nextInt(imgList.size)

        return imgList[pickedImg]
    }

    companion object {
        const val DETAIL_JSON_STRING = "ITEM"
    }
}