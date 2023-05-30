/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.abhiram.thorak.ui.theme

import android.media.RouteDiscoveryPreference
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val ThorakShapes = Shapes(
    small = RoundedCornerShape(percent = 50),
    medium = RoundedCornerShape(size = 8.dp),
    large = RoundedCornerShape(size = 10.dp)
)
val IconShapes = Shapes(
    extraSmall = RoundedCornerShape(percent = 0),
    small = RoundedCornerShape(percent = 25),
    medium = RoundedCornerShape(percent = 40),
    large = RoundedCornerShape(percent = 75)
)
