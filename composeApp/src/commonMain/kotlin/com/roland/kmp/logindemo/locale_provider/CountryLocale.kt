package com.roland.kmp.logindemo.locale_provider

object CountryLocale {

    fun getCountryDialingCode(countryCode: String) = getCountryDialCodes()[countryCode]

    fun getCountryCodes() =
        listOf(
            "AF", "AL", "DZ", "AS", "AD", "AO", "AI", "AQ", "AG", "AR", "AM", "AW", "AU", "AT", "AX", "AZ", "BS", "BH", "BD", "BB", "BY", "BE", "BZ", "BJ",
            "BM", "BT", "BO", "BA", "BW", "BR", "BN", "BG", "BF", "BL", "BQ", "BV", "BI", "KH", "CM", "CA", "CV", "CC", "CI", "CK", "CW", "CX", "KY", "CF",
            "TD", "CL", "CN", "CO", "KM", "CG", "CD", "CR", "HR", "CU", "CY", "CZ", "DK", "DJ", "DM", "DO", "EC", "EG", "SV", "GQ", "EH", "FK", "FO", "GF",
            "GG", "GP", "GS", "HK", "HM", "IM", "IO", "JE", "KN", "LC", "MF", "MO", "MP", "MQ", "MS", "NC", "PF", "PM", "PN", "PR", "PS", "RE", "SH", "SX",
            "TC", "TF", "TK", "UM", "VC", "VG", "VI", "WF", "YT", "ER", "EE", "ET", "FJ", "FI", "FR", "GA", "GM", "GE", "DE", "GH", "GI", "GR", "GL", "GD",
            "GU", "GT", "GN", "GW", "GY", "HT", "HN", "HU", "IS", "IN", "ID", "IR", "IQ", "IE", "IL", "IT", "JM", "JP", "JO", "KZ", "KE", "KI", "KP", "KR",
            "KW", "KG", "LA", "LV", "LB", "LS", "LR", "LY", "LI", "LT", "LU", "MG", "MW", "MY", "MV", "ML", "MT", "MH", "MR", "MU", "MX", "FM", "MD", "MC",
            "MN", "ME", "MA", "MZ", "MM", "NA", "NR", "NP", "NL", "NZ", "NI", "NE", "NG", "NU", "NF", "MK", "NO", "OM", "PK", "PW", "PA", "PG", "PY", "PE",
            "PH", "PL", "PT", "QA", "RO", "RU", "RW", "WS", "SM", "ST", "SA", "SN", "RS", "SC", "SL", "SG", "SK", "SI", "SB", "SO", "ZA", "SS", "ES", "LK",
            "SD", "SR", "SJ", "SZ", "SE", "CH", "SY", "TW", "TJ", "TZ", "TH", "TL", "TG", "TO", "TT", "TN", "TR", "TM", "TV", "UG", "UA", "AE", "GB", "US",
            "UY", "UZ", "VU", "VA", "VE", "VN", "YE", "ZM", "ZW"
        )

    private fun getCountryDialCodes() =
        mapOf(
            "AF" to "+93", "AL" to "+355", "DZ" to "+213", "AS" to "+1684", "AD" to "+376", "AO" to "+244", "AI" to "+1", "AQ" to "+672",
            "AG" to "+1", "AR" to "+54", "AM" to "+374", "AW" to "+297", "AU" to "+61", "AT" to "+43", "AX" to "+358", "AZ" to "+994",
            "BS" to "+1242", "BH" to "+973", "BD" to "+880", "BB" to "+1", "BY" to "+375", "BE" to "+32", "BZ" to "+501", "BJ" to "+229",
            "BM" to "+1", "BT" to "+975", "BO" to "+591", "BA" to "+387", "BW" to "+267", "BR" to "+55", "BN" to "+673", "BG" to "+359",
            "BF" to "+226", "BL" to "+590", "BQ" to "+599", "BV" to "+47", "BI" to "+257", "KH" to "+855", "CM" to "+237", "CA" to "+1",
            "CV" to "+238", "CC" to "+61", "CI" to "+225", "CK" to "+682", "CW" to "+599", "CX" to "+61", "KY" to "+1", "CF" to "+236",
            "TD" to "+235", "CL" to "+56", "CN" to "+86", "CO" to "+57", "KM" to "+269", "CG" to "+242", "CD" to "+243", "CR" to "+506",
            "HR" to "+385", "CU" to "+53", "CY" to "+357", "CZ" to "+420", "DK" to "+45", "DJ" to "+253", "DM" to "+1", "DO" to "+1", "EC" to "+593",
            "EG" to "+20", "SV" to "+503", "GQ" to "+240", "EH" to "+212", "FK" to "+500", "FO" to "+298", "GF" to "+594", "GG" to "+44", "GP" to "+590",
            "GS" to "+500", "HK" to "+852", "HM" to "+672", "IM" to "+44", "IO" to "+246", "JE" to "+44", "KN" to "+1", "LC" to "+1",
            "MF" to "+590", "MO" to "+853", "MP" to "+1", "MQ" to "+596", "MS" to "+1", "NC" to "+687", "PF" to "+689", "PM" to "+508",
            "PN" to "+870", "PR" to "+1", "PS" to "+970", "RE" to "+262", "SH" to "+290", "SX" to "+1", "TC" to "+1", "TF" to "+262",
            "TK" to "+690", "UM" to "+1", "VC" to "+1", "VG" to "+1", "VI" to "+1", "WF" to "+681", "YT" to "+262", "ER" to "+291", "EE" to "+372",
            "ET" to "+251", "FJ" to "+679", "FI" to "+358", "FR" to "+33", "GA" to "+241", "GM" to "+220", "GE" to "+995", "DE" to "+49",
            "GH" to "+233", "GI" to "+350", "GR" to "+30", "GL" to "+299", "GD" to "+1", "GU" to "+1", "GT" to "+502", "GN" to "+224", "GW" to "+245",
            "GY" to "+592", "HT" to "+509", "HN" to "+504", "HU" to "+36", "IS" to "+354", "IN" to "+91", "ID" to "+62", "IR" to "+98", "IQ" to "+964",
            "IE" to "+353", "IL" to "+972", "IT" to "+39", "JM" to "+1", "JP" to "+81", "JO" to "+962", "KZ" to "+7", "KE" to "+254", "KI" to "+686",
            "KP" to "+850", "KR" to "+82", "KW" to "+965", "KG" to "+996", "LA" to "+856", "LV" to "+371", "LB" to "+961", "LS" to "+266", "LR" to "+231",
            "LY" to "+218", "LI" to "+423", "LT" to "+370", "LU" to "+352", "MG" to "+261", "MW" to "+265", "MY" to "+60", "MV" to "+960", "ML" to "+223",
            "MT" to "+356", "MH" to "+692", "MR" to "+222", "MU" to "+230", "MX" to "+52", "FM" to "+691", "MD" to "+373", "MC" to "+377", "MN" to "+976",
            "ME" to "+382", "MA" to "+212", "MZ" to "+258", "MM" to "+95", "NA" to "+264", "NR" to "+674", "NP" to "+977", "NL" to "+31", "NZ" to "+64",
            "NI" to "+505", "NE" to "+227", "NG" to "+234", "NU" to "+683", "NF" to "+672", "MK" to "+389", "NO" to "+47", "OM" to "+968", "PK" to "+92",
            "PW" to "+680", "PA" to "+507", "PG" to "+675", "PY" to "+595", "PE" to "+51", "PH" to "+63", "PL" to "+48", "PT" to "+351", "QA" to "+974",
            "RO" to "+40", "RU" to "+7", "RW" to "+250", "WS" to "+685", "SM" to "+378", "ST" to "+239", "SA" to "+966", "SN" to "+221", "RS" to "+381",
            "SC" to "+248", "SL" to "+232", "SG" to "+65", "SK" to "+421", "SI" to "+386", "SB" to "+677", "SO" to "+252", "ZA" to "+27", "SS" to "+211",
            "ES" to "+34", "LK" to "+94", "SD" to "+249", "SR" to "+597", "SJ" to "+47", "SZ" to "+268", "SE" to "+46", "CH" to "+41", "SY" to "+963",
            "TW" to "+886", "TJ" to "+992", "TZ" to "+255", "TH" to "+66", "TL" to "+670", "TG" to "+228", "TO" to "+676", "TT" to "+1", "TN" to "+216",
            "TR" to "+90", "TM" to "+993", "TV" to "+688", "UG" to "+256", "UA" to "+380", "AE" to "+971", "GB" to "+44", "US" to "+1", "UY" to "+598",
            "UZ" to "+998", "VU" to "+678", "VA" to "+379", "VE" to "+58", "VN" to "+84", "YE" to "+967", "ZM" to "+260", "ZW" to "+263"
        )

}