package falabella.lakovratim.android.fastseller.data

import android.os.Environment
import falabella.lakovratim.android.fastseller.data.local.CounterDatabase
import falabella.lakovratim.android.fastseller.data.remote.CounterAPI
import falabella.lakovratim.android.fastseller.domain.model.WorkOrder
import falabella.lakovratim.android.fastseller.domain.repository.IRepository
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import javax.inject.Inject

/**
 * @author   Andres Lobosz
 * @date    25-09-20.
 */
class Repository @Inject constructor(
    private val database: CounterDatabase,
    private val counterAPI: CounterAPI
) : IRepository {
    override suspend fun getOrders(sellerId: String): List<WorkOrder> =
        counterAPI.getOrders(sellerId)


    override suspend fun sendOrder(
        sellerId: String,
        workerId: String,
        image: File?,
        comment: String?,
        latitude: Double?,
        longitude: Double?,
        success: Boolean,
    ): Boolean {

        /*  val requestFile: RequestBody? =
              image?.let { RequestBody.create("multipart/form-data".toMediaTypeOrNull(), it) }
          val imagePart: MultipartBody.Part? =
              requestFile?.let { MultipartBody.Part.createFormData("image", image.name, it) }*/

        val response = counterAPI.sendOrder(
            sellerId,
            workerId,
            null
            /*createFileForTest()*/,
            comment,
            latitude,
            longitude,
            success
        )
        return response?.isSuccessful ?: false

    }

    private fun createFileForTest(): File? {
        val file: File = File(
            Environment.getExternalStorageDirectory().toString() + "/" + File.separator + "test.txt"
        )
        file.createNewFile()
        val data1 = byteArrayOf(1, 1, 0, 0)
        if (file.exists()) {
            val fo: OutputStream = FileOutputStream(file)
            fo.write(data1)
            fo.close()
            println("file created: $file")
        }
        return file
    }
}
