package dev.nogipx.java.crazycars.preference

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.*
import dev.nogipx.java.crazycars.ExtraInfo
import dev.nogipx.java.crazycars.room.entity.Car
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation


class CarPreferenceFragment(val car: Car)
  : PreferenceFragmentCompat(),
  SharedPreferences.OnSharedPreferenceChangeListener {
  
  var onPauseFun: (Car) -> Unit = {}
  lateinit var prefs: MutableList<Preference>
  
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    PreferenceManager.getDefaultSharedPreferences(activity)
      .registerOnSharedPreferenceChangeListener(this)
    
    preferenceManager.sharedPreferences.edit().clear().apply()
  }
  
  override fun onCreatePreferences(p0: Bundle?, p1: String?) {
    
    val screen = preferenceManager.createPreferenceScreen(activity)
    
    prefs = mutableListOf()
    car::class.declaredMemberProperties.reversed().forEach { prop ->
      prop.findAnnotation<ExtraInfo>()?.let { ann ->
        val pref = EditTextPreference(activity).apply {
          title = ann.title
          key = prop.name
          summary = (prop as KProperty1<Car, String>).get(car)
          setDefaultValue(prop.get(car))
        }
        prefs.add(pref)
      }
    }
    
    prefs.forEach { screen.addPreference(it) }
    preferenceScreen = screen
  
  }

  override fun onSharedPreferenceChanged(preference: SharedPreferences?, key: String?) {
    val prop = car::class.declaredMemberProperties.find { it.name == key }
    
    if (prop is KMutableProperty<*>) {
      val newValue = preference!!.getString(key, "")
      prop.setter.call(car, newValue)
      
      prefs.find { it.key == key }?.let {
        it.summary = newValue
      }
    }
  }
  
  override fun onPause() {
    super.onPause()
    onPauseFun(car)
  }
}