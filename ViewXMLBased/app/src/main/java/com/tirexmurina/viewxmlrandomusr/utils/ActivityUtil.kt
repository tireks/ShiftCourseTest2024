package com.tirexmurina.viewxmlrandomusr.utils

import androidx.fragment.app.Fragment
import com.tirexmurina.viewxmlrandomusr.MainActivity

val Fragment.mainActivity: MainActivity
    get() = requireActivity() as MainActivity