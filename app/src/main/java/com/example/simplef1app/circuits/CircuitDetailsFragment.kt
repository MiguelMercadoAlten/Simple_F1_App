package com.example.simplef1app.circuits

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.simplef1app.R
import com.example.simplef1app.api.JsonTools
import com.example.simplef1app.data.database.entities.CircuitEntity
import com.example.simplef1app.databinding.FragmentCircuitDetailsBinding
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker


class CircuitDetailsFragment : Fragment()/*, MapListener*/ {
    private var itemJsonString : String? = null
    private lateinit var circuit : CircuitEntity

    private var _binding: FragmentCircuitDetailsBinding? = null
    private val binding get() = _binding!!

    lateinit var myMap: MapView
    lateinit var mapController: IMapController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Needed to show OSM tile
        Configuration.getInstance().userAgentValue = context?.packageName ?: "default"

        arguments?.let {
            itemJsonString = it.getString(DETAIL_JSON_STRING)
            circuit = JsonTools.gsonParser!!.fromJson(itemJsonString, CircuitEntity::class.java)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCircuitDetailsBinding.inflate(inflater, container, false)
        binding.circuitGivenNameDetailsTextView.text = circuit.circuitName

        val country = circuit.location?.country
        val city = circuit.location?.locality
        if (country != null && city !=  null) {
            val finalDescription = "$city - $country"
            binding.circuitCountryDetailsTextView.isVisible = true
            binding.circuitCountryDetailsTextView.text = finalDescription
        }

        myMap = binding.tackMapView
        myMap.setTileSource(TileSourceFactory.MAPNIK)
        myMap.setMultiTouchControls(true)
        myMap.getLocalVisibleRect(Rect())

        mapController = myMap.controller

        zoomToCircuit(mapController)

        return binding.root
    }

    private fun zoomToCircuit(mapController: IMapController?) {
        if (circuit.location?.lat != null && circuit.location?.lat != null){
            val (lat, long) = Pair(circuit.location?.lat!!.toDouble() , circuit.location?.long!!.toDouble())
            val mapPoint = GeoPoint(lat, long)
            mapController!!.animateTo(mapPoint, circuitZoom, zoomSpeed)

            val trackMarker = Marker(myMap)
            trackMarker.title = "$lat, $long"
            if (circuit.location?.locality != null) {
                trackMarker.snippet = circuit.location!!.locality
            }

            val d = ResourcesCompat.getDrawable(resources, R.drawable.ic_map_pin, null)
            trackMarker.icon = d
            trackMarker.position = mapPoint
            trackMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)

            myMap.overlays.add(trackMarker)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val circuitZoom = 16.5
        private const val zoomSpeed: Long = 1500

        const val DETAIL_JSON_STRING = "ITEM"
    }
}