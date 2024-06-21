package net.lyof.phantasm.entity.client.renderer;

import net.lyof.phantasm.Phantasm;
import net.lyof.phantasm.entity.client.ModModelLayers;
import net.lyof.phantasm.entity.client.model.BehemothModel;
import net.lyof.phantasm.entity.client.model.CrystieModel;
import net.lyof.phantasm.entity.custom.BehemothEntity;
import net.lyof.phantasm.entity.custom.CrystieEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BehemothRenderer extends MobEntityRenderer<BehemothEntity, BehemothModel<BehemothEntity>> {
    private static final Identifier TEXTURE = Phantasm.makeID("textures/entity/behemoth.png");

    public BehemothRenderer(EntityRendererFactory.Context context) {
        super(context, new BehemothModel<>(context.getPart(ModModelLayers.BEHEMOTH)), 0.6f);
    }

    @Override
    public Identifier getTexture(BehemothEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BehemothEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
