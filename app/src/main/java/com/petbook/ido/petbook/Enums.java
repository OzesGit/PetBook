package com.petbook.ido.petbook;

/**
 * Created by Gal on 12/08/2016.
 */
public class Enums {
    public enum Gender {
        MALE,
        FEMALE,
        UNKNOWN
    }

    public enum Type {
        DOG,
        CAT,
        TURTLE
    }

    public enum Age {
        UNKNOWN,
        ZERO_TO_FIVE_MONTHS,
        SIX_MONTHS_TO_YEAR,
        ONE_TO_FIVE_YEARS,
        SIX_TO_TEN_YEARS,
        MORE_THAN_TEN_YEARS
    }

    public enum DEALS_WITH {
        CHILDREN,
        DOGS,
        CATS

    }

    public enum CONDITIONS {
        CLOSEDAPPARTMENT,
        OPENAPPARTMENT,
        YARD,
        NO_MATTER
    }

    public enum LOCATIONS {
        NORTH,
        HADERA_NORTH_AMAKIM,
        HASHARON,
        MERKAZ,
        JERUSALEM,
        YEHUDA_AND_SHOMRON,
        SHFELA_AND_MISHOR_HOF_SOUTH,
        SOUTH
    }

}
