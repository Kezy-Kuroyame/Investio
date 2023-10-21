package for_sql
import com.google.gson.annotations.SerializedName
class Response {
    @SerializedName("status")
    private val status: String? = null

    @SerializedName("result_code")
    private val result_code = 0

    fun getStatus(): String? {
        return status
    }

    fun getResult_code(): Int {
        return result_code
    }
}