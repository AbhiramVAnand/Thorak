package com.abhiram.thorak

import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun LauncherScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val apps = remember { getLaunchableApps(context) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 21.dp, vertical = 12.dp)
    ) {
        Spacer(
            modifier = Modifier.weight(1F)
        )
        AppGrid(apps = apps) { pkgName ->
            launchApp(context, pkgName)
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Rounded.Email,
                contentDescription = "app icon",
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(64.dp)
            )
            Spacer(modifier = Modifier.weight(1F))
            Icon(
                imageVector = Icons.Rounded.Email,
                contentDescription = "app icon",
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(64.dp)
            )
        }
    }
}

fun getLaunchableApps(context: Context): List<AppInfo> {
    val pm = context.packageManager

    val intent = Intent(Intent.ACTION_MAIN, null).apply {
        addCategory(Intent.CATEGORY_LAUNCHER)
    }

    val launchableApps: List<ResolveInfo> = pm.queryIntentActivities(intent, 0)

    return launchableApps.map {
        val label = pm.getApplicationLabel(it.activityInfo.applicationInfo).toString()
        val packageName = it.activityInfo.applicationInfo.packageName
        val icon = pm.getApplicationIcon(packageName)
        AppInfo(name = label, packageName = packageName, icon = icon)
    }.sortedBy { it.name.lowercase() }
}

fun launchApp(context: Context, packageName: String) {
    val launchIntent = context.packageManager.getLaunchIntentForPackage(packageName)
    if (launchIntent != null) {
        context.startActivity(launchIntent)
    }
}


data class AppInfo(
    val name: String,
    val packageName: String,
    val icon: Drawable
)


@Composable
fun AppGrid(apps: List<AppInfo>, onAppClick: (String) -> Unit) {
    LazyRow(
        modifier = Modifier.padding(16.dp)
    ) {
        items(apps.size) { index ->
            val app = apps[index]
            val imageBitmap = remember(app.icon) { drawableToImageBitmap(app.icon) }
            Image(
                bitmap = imageBitmap,
                contentDescription = "app icon",
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(64.dp)
                    .clickable { onAppClick(app.packageName) }
            )
        }
    }
}

fun drawableToImageBitmap(drawable: Drawable): ImageBitmap {
    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth.coerceAtLeast(1),
        drawable.intrinsicHeight.coerceAtLeast(1),
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap.asImageBitmap()
}