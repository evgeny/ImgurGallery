package com.ezino.imgurgallery

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.PopupMenu
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.ezino.imgurgallery.adapters.GalleryAdapter
import com.ezino.imgurgallery.adapters.ImageDiffCallback
import com.ezino.imgurgallery.model.Section
import com.ezino.imgurgallery.utils.mapToItemId
import com.ezino.imgurgallery.viewmodels.ImageListViewModel
import kotlinx.android.synthetic.main.activity_gallery.*


class GalleryActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {
    /**
     * viewmodel for this view e.g. activity
     */
    private lateinit var viewModel: ImageListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        setSupportActionBar(toolbar)

        val adapter = GalleryAdapter(ImageDiffCallback(), applicationContext)
        list_view.adapter = adapter
        list_view.setHasFixedSize(true)

        viewModel = ViewModelProviders.of(this).get(ImageListViewModel::class.java)
        viewModel.getImages().observe(this, Observer { images -> adapter.submitList(images) })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_gallery, menu)
        val actionViral = menu.findItem(R.id.action_viral)
        val icon = if (viewModel.showViral) R.drawable.ic_visibility_white else R.drawable.ic_visibility_off_white
        actionViral?.setIcon(icon)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                // TODO
                Log.d("TAG", "show about fragment")
                return true
            }
            R.id.action_section -> {
                showSectionPopup()
                return true
            }
            R.id.action_viral -> {
                viewModel.showViral = !viewModel.showViral
                invalidateOptionsMenu()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * click callback for section popup menu
     */
    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.hot -> {
                viewModel.selectedSection = Section.HOT
                return true
            }
            R.id.top -> {
                viewModel.selectedSection = Section.TOP
                return true
            }
            R.id.user -> {
                viewModel.selectedSection = Section.USER
                return true
            }
            else -> true
        }
    }

    /**
     * show section popup menu
     */
    private fun showSectionPopup() {
        val popup = PopupMenu(this, findViewById(R.id.action_section))
        val inflater = popup.menuInflater
        inflater.inflate(R.menu.menu_section, popup.menu)
        popup.setOnMenuItemClickListener(this)
        popup.menu.findItem(mapToItemId(viewModel.selectedSection)).isChecked = true
        popup.show()
    }
}