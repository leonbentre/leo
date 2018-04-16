package leonbentre.struct.CMD

import leonbentre.deserializer.channel.ActorChannel.Companion.droppedItemToItem
import leonbentre.struct.Actor
import leonbentre.struct.Bunch
import leonbentre.struct.NetGuidCacheObject
import leonbentre.util.debugln

object DroppedItemCMD
{

  fun process(actor : Actor, bunch : Bunch, repObj : NetGuidCacheObject?, waitingHandle : Int, data : HashMap<String, Any?>) : Boolean
  {
    try
    {
      with(bunch) {
        when (waitingHandle)
        {
          16   ->
          {
            val (itemguid, item) = readObject()
            droppedItemToItem[actor.netGUID] = itemguid
          }
          else -> ActorCMD.process(actor, bunch, repObj, waitingHandle, data)
        }
        return true
      }
    }
    catch (e : Exception)
    {
      debugln { ("DroppedItemCMD is throwing somewhere: $e ${e.stackTrace} ${e.message} ${e.cause}") }
    }
    return false
  }
}
