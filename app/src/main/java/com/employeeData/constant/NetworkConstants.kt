package com.employeeData.constant

interface NetworkConstants {
    companion object {
        /*
            IP Address
        */
        private const val DEVELOPMENT_IP_ADDRESS = "ashok.gnecclienttesting.com"

        /*
            Base URL
        */
        const val BASE_URL_FOR_DEVELOPMENT = "https://$DEVELOPMENT_IP_ADDRESS/apptest/"

        const val EMPLOYEE_LIST = "get_experience.php"
        const val ADD_EXPERIENCE = "add_experience.php"
    }
}