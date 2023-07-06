package com.cst.taipeitour.ui.main.list

import android.view.View
import com.cst.taipeitour.R
import com.cst.taipeitour.data.entities.item.ListItemEntity
import com.cst.taipeitour.data.network.response.ResponseData
import com.cst.taipeitour.databinding.ItemListBinding
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem


class ListItem(
    private val data: ResponseData.Data,
    private val itemClickListener: ItemClickListener
) : BindableItem<ItemListBinding>() {

    override fun getLayout() = R.layout.item_list

    override fun initializeViewBinding(view: View) = ItemListBinding.bind(view)

    override fun bind(binding: ItemListBinding, position: Int) {

        with(binding) {

            click = View.OnClickListener { itemClickListener.onClick(data) }
            entity = data.toListItemEntity()

            content.viewTreeObserver.addOnGlobalLayoutListener { content.maxLines = content.height / content.lineHeight - 1 }
        }

        data.images.getOrNull(0)?.let {
            Picasso.get().load(it.src).into(binding.image)
        }
    }

    private fun ResponseData.Data.toListItemEntity() = ListItemEntity(
        title = this.name,
        content = this.introduction
    )

    fun interface ItemClickListener {
        fun onClick(data: ResponseData.Data)
    }
}