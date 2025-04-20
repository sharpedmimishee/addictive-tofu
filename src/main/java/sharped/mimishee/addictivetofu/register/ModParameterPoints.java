package sharped.mimishee.addictivetofu.register;

import net.minecraft.world.level.biome.Climate;

public class ModParameterPoints {
    private static final Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1.0F, 1.0F);
    private static final Climate.Parameter[] temperatures = new Climate.Parameter[]{
            Climate.Parameter.span(-1.0F, -0.45F),
            Climate.Parameter.span(-0.45F, -0.15F),
            Climate.Parameter.span(-0.15F, 0.2F),
            Climate.Parameter.span(0.2F, 0.55F),
            Climate.Parameter.span(0.55F, 1.0F)
    };
    private static final Climate.Parameter[] humidities = new Climate.Parameter[]{
            Climate.Parameter.span(-1.0F, -0.35F),
            Climate.Parameter.span(-0.35F, -0.1F),
            Climate.Parameter.span(-0.1F, 0.1F),
            Climate.Parameter.span(0.1F, 0.3F),
            Climate.Parameter.span(0.3F, 1.0F)
    };
    private static final Climate.Parameter[] erosions = new Climate.Parameter[]{
            Climate.Parameter.span(-1.0F, -0.78F),
            Climate.Parameter.span(-0.78F, -0.375F),
            Climate.Parameter.span(-0.375F, -0.2225F),
            Climate.Parameter.span(-0.2225F, 0.05F),
            Climate.Parameter.span(0.05F, 0.45F),
            Climate.Parameter.span(0.45F, 0.55F),
            Climate.Parameter.span(0.55F, 1.0F)
    };
    private static final Climate.Parameter mushroomFieldsContinentalness = Climate.Parameter.span(-1.2F, -1.05F);
    private static final Climate.Parameter deepOceanContinentalness = Climate.Parameter.span(-1.05F, -0.455F);
    private static final Climate.Parameter oceanContinentalness = Climate.Parameter.span(-0.455F, -0.19F);
    private static final Climate.Parameter coastContinentalness = Climate.Parameter.span(-0.19F, -0.11F);
    private static final Climate.Parameter inlandContinentalness = Climate.Parameter.span(-0.11F, 0.55F);
    private static final Climate.Parameter nearInlandContinentalness = Climate.Parameter.span(-0.11F, 0.03F);
    private static final Climate.Parameter midInlandContinentalness = Climate.Parameter.span(0.03F, 0.3F);
    private static final Climate.Parameter farInlandContinentalness = Climate.Parameter.span(0.3F, 1.0F);


    public static final Climate.ParameterPoint REDBEAN_FOREST = new Climate.ParameterPoint(
            Climate.Parameter.span(temperatures[2], temperatures[3]),
            Climate.Parameter.span(humidities[2], humidities[3]),
            Climate.Parameter.span(midInlandContinentalness, farInlandContinentalness),
            Climate.Parameter.span(erosions[2], erosions[4]),
            Climate.Parameter.point(0),
            FULL_RANGE,
            0
    );
    public static final Climate.ParameterPoint REDBEAN_FOREST_UP = new Climate.ParameterPoint(
            Climate.Parameter.span(temperatures[2], temperatures[3]),
            Climate.Parameter.span(humidities[2], humidities[3]),
            Climate.Parameter.span(midInlandContinentalness, farInlandContinentalness),
            Climate.Parameter.span(erosions[2], erosions[4]),
            Climate.Parameter.point(1),
            FULL_RANGE,
            0
    );

    public static final Climate.ParameterPoint TEST = new Climate.ParameterPoint(
            FULL_RANGE,
            FULL_RANGE,
            FULL_RANGE,
            Climate.Parameter.span(erosions[0], erosions[1]),
            Climate.Parameter.point(1.1F),
            FULL_RANGE,
            0
    );
}
