package io.github.affable_armors;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.payload.CustomPayload;
import net.minecraft.util.Identifier;

public class AffableNetworking {
	public static final Identifier CHECK_PACKET_ID = AffableArmors.id("effect");
	public static final Identifier RESULT_PACKET_ID = AffableArmors.id("effect");

	public record CheckEntityPayload(String checkEntity) implements CustomPayload {
		public static final CustomPayload.Id<CheckEntityPayload> ID = new CustomPayload.Id<>(CHECK_PACKET_ID);
		public static final PacketCodec<RegistryByteBuf, CheckEntityPayload> CODEC = PacketCodec.tuple(
			PacketCodecs.string(256), CheckEntityPayload::checkEntity,
			CheckEntityPayload::new
		);

		@Override
		public CustomPayload.Id<? extends CustomPayload> getId() {
			return ID;
		}
	}
	public record ResultPayload(Boolean result) implements CustomPayload {
		public static final CustomPayload.Id<ResultPayload> ID = new CustomPayload.Id<>(RESULT_PACKET_ID);
		public static final PacketCodec<RegistryByteBuf, ResultPayload> CODEC = PacketCodec.tuple(
			PacketCodecs.BOOL, ResultPayload::result,
			ResultPayload::new
		);

		@Override
		public Id<? extends CustomPayload> getId() {
			return ID;
		}
	}
}
