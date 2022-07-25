package com.rdevl.sportplug2.data.entries

import android.os.Parcel
import android.os.Parcelable

data class DetailMatch(
    val brId: Int,
    val brTeam1Id: Any,
    val brTeam2Id: Any,
    val champId: Int,
    val champName: String,
    val comment: String,
    val id: Int,
    val liveDelay: Any,
    val markets: List<MarketDetail>,
    val maxBet: Int,
    val mnum: Int,
    val name: String,
    val order: Int,
    val outcomeCount: Int,
    val parentId: Any,
    val score: String,
    val specVal: Int,
    val sportId: Int,
    val sportName: String,
    val start: Long,
    val state: String
): Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
    }
}

data class MarketDetail(
    val category: Int,
    val groupName: String,
    val groupPos: Int,
    val id: Long,
    val liveDelay: Any,
    val main: Boolean,
    val market: Int,
    val maxbet: Int,
    val maxmisc: Int,
    val maxpayment: Any,
    val name: String,
    val outcomeType: Int,
    val `param`: Any,
    val period: Any,
    val playerId: Any,
    val pos: Int,
    val selection: Int,
    val shortName: String,
    val usemaxpay: Any,
    val value: String
)

data class PostChampsBody(
    val sport_id: Int,
    val live: Int,
    val lang_id: Int,
    val platforma: String
)

data class Matches(
    val champ: ChampMatch,
    val events: List<Event>
)

data class ChampMatch(
    val comment: String,
    val eventsCount: Int,
    val id: Int,
    val maxBet: Int,
    val name: String,
    val order: Int,
    val sportId: Int,
    val sportName: String,
    val startDate: Int,
    val stat: Boolean,
    val top: Boolean,
    val total: Boolean
)

data class Event(
    val brId: Int?,
    val champId: Int?,
    val champName: String,
    val id: Int,
    val name: String?,
    val score: String,
    val sportId: Int,
    val sportName: String,
    val start: Long,
)

data class Champs(
    val `data`: List<Data>,
    val debug: String,
    val error: Error,
    val isCache: Int
)

data class Data(
    val cgi: Int,
    val cid: Int,
    val co: Int,
    val com: String,
    val id: Int,
    val io: Int,
    val `is`: Boolean,
    val max: Int,
    val n: String,
    val sn: String,
    val so: Int,
    val sort: Int,
    val top: Int,
    val top_b: Int
)

data class Error(
    val err_code: Int,
    val err_desc: Any
)