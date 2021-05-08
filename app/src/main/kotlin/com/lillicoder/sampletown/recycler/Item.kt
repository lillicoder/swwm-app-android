package com.lillicoder.sampletown.recycler

import androidx.annotation.DrawableRes

/**
 * Represents an arbitrary item.
 * @param imageResourceId Image resource ID.
 */
data class Item(@DrawableRes val imageResourceId: Int)
