package sharped.mimishee.addictivetofu.register;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import sharped.mimishee.addictivetofu.AddictiveTofu;
import sharped.mimishee.addictivetofu.world.gen.structure.mansion.TofuMansionPieces;

import java.util.Locale;

public interface TofuStructurePieceType {
    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPE = DeferredRegister.create(BuiltInRegistries.STRUCTURE_PIECE, AddictiveTofu.MODID);

    DeferredHolder<StructurePieceType, StructurePieceType> TOFU_MANSION_PIECE = setTemplatePieceId(TofuMansionPieces.WoodlandMansionPiece::new, "tofu_mansion");

    private static DeferredHolder<StructurePieceType, StructurePieceType> setFullContextPieceId(StructurePieceType pieceType, String pieceId) {
        return STRUCTURE_PIECE_TYPE.register(pieceId.toLowerCase(Locale.ROOT), () -> pieceType);
    }

    private static DeferredHolder<StructurePieceType, StructurePieceType> setPieceId(StructurePieceType.ContextlessType type, String key) {
        return setFullContextPieceId(type, key);
    }

    private static DeferredHolder<StructurePieceType, StructurePieceType> setTemplatePieceId(StructurePieceType.StructureTemplateType templateType, String pieceId) {
        return setFullContextPieceId(templateType, pieceId);
    }
}
