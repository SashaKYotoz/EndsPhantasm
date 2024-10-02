package net.lyof.phantasm.entity.client.renderer;

import net.lyof.phantasm.Phantasm;
import net.lyof.phantasm.block.custom.GravityCoreBlock;
import net.lyof.phantasm.block.entity.GravityCoreBlockEntity;
import net.lyof.phantasm.entity.client.ModModelLayers;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.ConduitBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class GravityCoreRenderer<T extends GravityCoreBlockEntity> implements BlockEntityRenderer<T> {
    private static final Identifier TEXTURE = Phantasm.makeID("textures/entity/gravity_core.png");
    private ModelPart model;

    public GravityCoreRenderer(BlockEntityRendererFactory.Context context) {
        this.model = context.getLayerModelPart(ModModelLayers.GRAVITY_CORE);
    }

    @Override
    public void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        VertexConsumer vertex = vertexConsumers.getBuffer(RenderLayer.getEntityCutout(TEXTURE));
        this.model.render(matrices, vertex, light, overlay);
    }
}
