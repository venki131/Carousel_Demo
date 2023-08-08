package com.example.carousel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.carousel.databinding.FragmentCarouselBinding
import com.example.carousel.util.ItemFadeOnScrollListener
import com.example.carousel.util.addOnItemFadeScrollListener
import com.example.carousel.util.startAutoScroll
import com.mig35.carousellayoutmanager.CarouselLayoutManager
import com.mig35.carousellayoutmanager.CarouselZoomPostLayoutListener
import com.mig35.carousellayoutmanager.CenterScrollListener

class CarouselFragment : Fragment() {

    private var _binding: FragmentCarouselBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CarouselRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarouselBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        binding.recyclerview.adapter = adapter
    }

    private val list = mutableListOf(
        R.drawable.building1,
        R.drawable.building2,
        R.drawable.building3,
        R.drawable.building4,
        R.drawable.building5
    )

    private fun initRecyclerView() {
        recyclerView = binding.recyclerview

        val layoutManager = CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true)
        layoutManager.setPostLayoutListener(CarouselZoomPostLayoutListener(0.4f))
        layoutManager.maxVisibleItems = 1
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = CarouselRvAdapter(list)
        recyclerView.addOnScrollListener(CenterScrollListener())
        //recyclerView.startAutoScroll(3000L)
        recyclerView.addOnScrollListener(ItemFadeOnScrollListener())
    }
}