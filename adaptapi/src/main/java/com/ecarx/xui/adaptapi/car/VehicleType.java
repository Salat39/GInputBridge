package com.ecarx.xui.adaptapi.car;

public enum VehicleType {
    COMMON(0),
    L541(1),
    V542_V90(2),
    V543_V90CC(3),
    V526(4),
    V426(5),
    V541_S90(6),
    V316(7),
    V320(8),
    V323(9),
    V431(10),
    V432(11),
    L431(12),
    V433(13),
    P514(14),
    V331(15),
    V317(16),
    CX11(128),
    CS11(129),
    CH11(130),
    CC11(131),
    CS12(132),
    DCY11(133),
    DX11(134),
    FX11(135),
    LAMBDA(138),
    HX11(139),
    HC11(140),
    EX11(161),
    KX11(162),
    DC1E(163),
    FW11(164),
    ES11(165),
    BC1E(166),
    EF1E(167),
    DX1E(168),
    CF1E(169),
    CM1E(170),
    G733(172),
    V216(173),
    BX1E(174),
    FS11(175),
    G636(176),
    CX21(177),
    CS21(178),
    P417(179),
    E155(180),
    CS1E(181),
    EO11(182),
    V51DK(183),
    E335(184),
    CM2E(185),
    CX1E(186),
    E171(187),
    E371(188),
    HRE1(224),
    LCV1(225),
    G426(226),
    MC11(227);

    private final int type;

    VehicleType(int i) {
        this.type = i;
    }

    public final int getType() {
        return this.type;
    }

    public static VehicleType get(int i) {
        switch (i) {
            case 1:
                return L541;
            case 2:
                return V542_V90;
            case 3:
                return V543_V90CC;
            case 4:
                return V526;
            case 5:
                return V426;
            case 6:
                return V541_S90;
            case 7:
                return V316;
            case 8:
                return V320;
            case 9:
                return V323;
            case 10:
                return V431;
            case 11:
                return V432;
            case 12:
                return L431;
            case 13:
                return V433;
            case 14:
                return P514;
            case 15:
                return V331;
            case 16:
                return V317;
            default:
                switch (i) {
                    case 128:
                        return CX11;
                    case 129:
                        return CS11;
                    case 130:
                        return CH11;
                    case 131:
                        return CC11;
                    case 132:
                        return CS12;
                    case 133:
                        return DCY11;
                    case 134:
                        return DX11;
                    case 135:
                        return FX11;
                    default:
                        switch (i) {
                            case 138:
                                return LAMBDA;
                            case 139:
                                return HX11;
                            case 140:
                                return HC11;
                            default:
                                switch (i) {
                                    case 161:
                                        return EX11;
                                    case 162:
                                        return KX11;
                                    case 163:
                                        return DC1E;
                                    case 164:
                                        return FW11;
                                    case 165:
                                        return ES11;
                                    case 166:
                                        return BC1E;
                                    case 167:
                                        return EF1E;
                                    case 168:
                                        return DX1E;
                                    case 169:
                                        return CF1E;
                                    case 170:
                                        return CM1E;
                                    default:
                                        switch (i) {
                                            case 172:
                                                return G733;
                                            case 173:
                                                return V216;
                                            case 174:
                                                return BX1E;
                                            case 175:
                                                return FS11;
                                            case 176:
                                                return G636;
                                            case 177:
                                                return CX21;
                                            case 178:
                                                return CS21;
                                            case 179:
                                                return P417;
                                            case 180:
                                                return E155;
                                            case 181:
                                                return CS1E;
                                            case 182:
                                                return EO11;
                                            case 183:
                                                return V51DK;
                                            case 184:
                                                return E335;
                                            case 185:
                                                return CM2E;
                                            case 186:
                                                return CX1E;
                                            case 187:
                                                return E171;
                                            case 188:
                                                return E371;
                                            default:
                                                switch (i) {
                                                    case 224:
                                                        return HRE1;
                                                    case 225:
                                                        return LCV1;
                                                    case 226:
                                                        return G426;
                                                    case 227:
                                                        return MC11;
                                                    default:
                                                        return COMMON;
                                                }
                                        }
                                }
                        }
                }
        }
    }
}
