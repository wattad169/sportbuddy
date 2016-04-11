/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.mostafawattad.sportbuddy;
import java.util.Map;
import java.util.HashMap;

public class Constants {

    /**
     * Account type string.
     */
    public static final String ACCOUNT_TYPE = "com.example.android.samplesync";

    public static final String RESPONSE_STATUS = "status";

    public static final String RESPONSE_OK = "OK";

    public static final String RESPONSE_MESSAGE = "message";

    /**
     * Authtoken type string.
     */
    public static final String AUTHTOKEN_TYPE = "com.example.android.samplesync";

    public static final String EVENT_TYPE = "type";

    public static final String EVENT_DATE = "date";

    public static final String EVENT_LOCATION = "formatted_location";

    public static final String EVENT_MEMBERS = "members";

    public static final String EVENT_NAME = "name";

    static final Map<String , Integer> sportTypeToLogo = new HashMap<String , Integer>() {{
        put("football", R.mipmap.football);
        put("swimming", R.mipmap.swimming);
        put("football", R.mipmap.football);
        put("soccer", R.mipmap.soccer);
        put("basketball", R.mipmap.basketball);
        put("running", R.mipmap.running);
        put("vollyball", R.mipmap.vollyball);
        put("cycling", R.mipmap.cycling);
        put("tenis", R.mipmap.tenis);
    }};

    public static final String LOGGEDUSERID = "loggedUserId";
    public static final int myEventsMode = 1;
    public static final int allEventsMode = 2;
    public static final String eventsMode = "eventMode";

}
