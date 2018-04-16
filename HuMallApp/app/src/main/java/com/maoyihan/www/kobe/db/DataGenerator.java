/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.maoyihan.www.kobe.db;

import com.maoyihan.www.kobe.db.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates data to pre-populate the database
 */
public class DataGenerator {

    private static final String[] FIRST = new String[]{
            "Special edition", "New", "Cheap", "Quality", "Used"};
    private static final String[] SECOND = new String[]{
            "Three-headed Monkey", "Rubber Chicken", "Pint of Grog", "Monocle"};
    private static final String[] MM = new String[]{
            "MC", "KB", "KD", "LBJ"};


    public static List<UserEntity> generateUsers() {
        List<UserEntity> userEntities = new ArrayList<>();
        for (int i = 0; i < MM.length; i++) {
            UserEntity product = new UserEntity();
            product.setName(MM[ i ]);
            product.setAddress(SECOND[ i ]);
            userEntities.add(product);
        }
        return userEntities;
    }

}
