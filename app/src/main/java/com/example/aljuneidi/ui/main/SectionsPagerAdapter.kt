package com.example.aljuneidi.ui.main
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import com.example.aljuneidi.customers.CustomerHomePage
import com.example.aljuneidi.customers.CustomerInvoice
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager,var customerBarcode:String) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when(position){
            0->{
                val bundle =Bundle()
                var fragment=CustomerHomePage()
                bundle.putString("CustomerBarcode", customerBarcode)
                fragment.setArguments(bundle)
                return fragment
            }
            1->{
                return CustomerInvoice()
            }else -> return null
        }


    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0-> return "معلومات الزبون"
            1-> return  "العمليات"
        }
        return null
    }

    override fun getCount(): Int {
        return 2
    }

}
