package com.cow.rocketmq.client.codec;

import io.netty.buffer.ByteBuf;
import java.util.List;

public abstract interface Decoder
{
  public abstract void decoder(ByteBuf paramByteBuf, List<Object> paramList, CodecContext paramCodecContext)
    throws Exception;
}
