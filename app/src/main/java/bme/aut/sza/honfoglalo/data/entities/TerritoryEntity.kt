package bme.aut.sza.honfoglalo.data.entities

enum class TerritoryEntity(val FullName: String, val idName: String) {
    BACS("Bács-Kiskun", "BK"),
    BARANYA("Baranya", "BARANYA"),
    BEKES("Békés", "BEKES"),
    BORSOD("Borsod-Abaúj-Zemplén", "BAZ"),
    CSONGRAD("Csongrád", "CSONGRAD"),
    FEJER("Fejér", "FEJER"),
    GYOR("Győr-Moson-Sopron", "GYMS"),
    HAJDU("Hajdú-Bihar", "HB"),
    HEVES("Heves", "HEVES"),
    JASZ("Jász-Nagykun-Szolnok", "JNSZ"),
    KOMAROM("Komárom-Esztergom", "KE"),
    NOGRAD("Nógrád", "NOGRAD"),
    PEST("Pest", "PEST"),
    SOMOGY("Somogy", "SOMOGY"),
    SZABOLCS("Szabolcs-Szatmár-Bereg", "SZSZB"),
    TOLNA("Tolna", "TOLNA"),
    VAS("Vas", "VAS"),
    VESZPREM("Veszprém", "VESZPREM"),
    ZALA("Zala", "ZALA");

    companion object {
        fun getID(name: String) : String{
            for (t in TerritoryEntity.entries){
                if(t.FullName == name){
                    return t.idName
                }
            }
            return ""
        }

        fun getName(name: String) : String{
            for (t in TerritoryEntity.entries){
                if(t.idName == name){
                    return t.FullName
                }
            }
            return ""
        }
    }
}
