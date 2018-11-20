package com.example.bruno.kljvissenakenapp.Gallerij

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bruno.kljvissenakenapp.R

class GalleryFragment:Fragment(){

    var image_titles = mutableListOf("img1", "img2", "img3", "img4", "img5", "img6", "img7", "img8", "img9", "img10")

    var image_ids = mutableListOf(R.drawable.pic1min,R.drawable.pic2min, R.drawable.pic3min, R.drawable.pic4min,
        R.drawable.pic5min, R.drawable.pic6min, R.drawable.pic7min, R.drawable.pic8min, R.drawable.pic9min, R.drawable.pic10min)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.gallery_title)
        return inflater?.inflate(R.layout.fragment_gallery, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var recycler = view.findViewById<RecyclerView>(R.id.galleryRecycler)

        var layoutManager:RecyclerView.LayoutManager = GridLayoutManager(activity?.applicationContext, 2)
        recycler.layoutManager = layoutManager

        var photos = prepareData()
        val adapter = GalleryAdapter(photos,activity!!.applicationContext )
        adapter.setHasStableIds(true)
        recycler.adapter = adapter
    }

    private fun prepareData():MutableList<Photo>{
        var images = mutableListOf<Photo>()

        for (i in 0..(image_titles.count()-1)){
            var item = Photo(image_titles[i],image_ids[i])
            //var item = Photo(image_titles[i],R.drawable.pic9)
            images.add(item)
        }

        return images
    }
}