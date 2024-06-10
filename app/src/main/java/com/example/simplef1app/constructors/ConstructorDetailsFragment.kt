package com.example.simplef1app.constructors

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.simplef1app.R
import com.example.simplef1app.api.JsonTools
import com.example.simplef1app.data.database.entities.ConstructorEntity
import com.example.simplef1app.databinding.FragmentConstructorDetailsBinding
import java.util.*

class ConstructorDetailsFragment : Fragment() {
    private var itemJsonString : String? = null
    private lateinit var constructor : ConstructorEntity

    private var _binding: FragmentConstructorDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemJsonString = it.getString(DETAIL_JSON_STRING)
            constructor = JsonTools.gsonParser!!.fromJson(itemJsonString, ConstructorEntity::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConstructorDetailsBinding.inflate(inflater, container, false)
        binding.constructorNameDetailsTextView.text = constructor.name
        binding.constructorIdDetailsTextView.text = constructor.constructorId
        binding.constructorNationalityDetailsTextView.text = constructor.nationality

        Glide.with(requireContext()).load(randomImage()).into(binding.constructorPictureDetailsImageView)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun randomImage(): Int {
        val imgList: List<Int> = listOf(
            R.drawable.ferrari,
            R.drawable.red_bull_racing,
            R.drawable.alpine,
            R.drawable.mercedespng
        )
        val rand = Random()
        val pickedImg = rand.nextInt(imgList.size)

        return imgList[pickedImg]
    }

    companion object {
        const val DETAIL_JSON_STRING = "ITEM"
    }
}