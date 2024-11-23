package bme.aut.sza.honfoglalo.data.entities

import bme.aut.sza.honfoglalo.R

enum class TerritoryEntity(val FullName: String,val idName: String, val Resource: Int) {
    BACS("Bács-Kiskun", "BK", R.drawable.bacs),
    BARANYA("Baranya","BARANYA", R.drawable.baranya),
    BEKES("Békés", "BEKES",R.drawable.bekes),
    BORSOD("Borsod-Abaúj-Zemplén", "BAZ", R.drawable.borsod),
    CSONGRAD("Csongrád", "CSONGRAD", R.drawable.csongrad),
    FEJER("Fejér","FEJER", R.drawable.fejer),
    GYOR("Győr-Moson-Sopron","GYMS", R.drawable.gyor),
    HAJDU("Hajdú-Bihar","HB", R.drawable.hajdu),
    HEVES("Heves","HEVES", R.drawable.heves),
    JASZ("Jász-Nagykun-Szolnok","JNSZ", R.drawable.jasz),
    KOMAROM("Komárom-Esztergom","KE", R.drawable.komarom),
    NOGRAD("Nógrád","NOGRAD", R.drawable.nograd),
    PEST("Pest","PEST", R.drawable.pest),
    SOMOGY("Somogy","SOMOGY", R.drawable.somogy),
    SZABOLCS("Szabolcs-Szatmár-Bereg","SZSZB", R.drawable.szabolcs),
    TOLNA("Tolna","TOLNA", R.drawable.tolna),
    VAS("Vas", "VAS",R.drawable.vas),
    VESZPREM("Veszprém", "VESZPREM", R.drawable.veszprem),
    ZALA("Zala", "ZALA", R.drawable.zala);

    companion object {
        fun getID(name: String) : String{
            for (t in TerritoryEntity.entries){
                if(t.FullName == name){
                    return t.idName
                }
            }
            return ""
        }
    }
}
