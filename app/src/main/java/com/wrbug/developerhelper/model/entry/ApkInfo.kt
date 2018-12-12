package com.wrbug.developerhelper.model.entry

import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import com.wrbug.developerhelper.basecommon.BaseApp

class ApkInfo(val packageInfo: PackageInfo, val applicationInfo: ApplicationInfo) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readParcelable(PackageInfo::class.java.classLoader),
        parcel.readParcelable(ApplicationInfo::class.java.classLoader)
    )

    fun getIco(): Drawable {
        return applicationInfo.loadIcon(BaseApp.instance.packageManager)
    }

    fun getAppName(): String {
        val label = BaseApp.instance.packageManager.getApplicationLabel(applicationInfo)
        return if (label == null) "" else label.toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(packageInfo, flags)
        parcel.writeParcelable(applicationInfo, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ApkInfo> {
        override fun createFromParcel(parcel: Parcel): ApkInfo {
            return ApkInfo(parcel)
        }

        override fun newArray(size: Int): Array<ApkInfo?> {
            return arrayOfNulls(size)
        }
    }

}