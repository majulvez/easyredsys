package com.miguelangeljulvez.easyredsys.client.util;


// @author noblemaster (Christoph Aschwanden)
public enum Currency {

    AED(784, Digits.DIGITS_2, "United Arab Emirates dirham", new Country[] { Country.AE }),
    AFN(971, Digits.DIGITS_2, "Afghan afghani", new Country[] { Country.AF }),
    ALL(8,   Digits.DIGITS_2, "Albanian lek", new Country[] { Country.AL }),
    AMD(51,  Digits.DIGITS_2, "Armenian dram", new Country[] { Country.AM }),
    ANG(532, Digits.DIGITS_2, "Netherlands Antillean guilder", new Country[] { Country.CW, Country.SX }),
    AOA(973, Digits.DIGITS_2, "Angolan kwanza", new Country[] { Country.AO }),
    ARS(32,  Digits.DIGITS_2, "Argentine peso", new Country[] { Country.AR }),
    AUD(36,  Digits.DIGITS_2, "Australian dollar", new Country[] { Country.AU, Country.CX, Country.CC, Country.HM, Country.KI, Country.NR, Country.NF, Country.TV }),
    AWG(533, Digits.DIGITS_2, "Aruban florin", new Country[] { Country.AW }),
    AZN(944, Digits.DIGITS_2, "Azerbaijani manat", new Country[] { Country.AZ }),
    BAM(977, Digits.DIGITS_2, "Bosnia and Herzegovina convertible mark", new Country[] { Country.BA }),
    BBD(52,  Digits.DIGITS_2, "Barbados dollar", new Country[] { Country.BB }),
    BDT(50,  Digits.DIGITS_2, "Bangladeshi taka", new Country[] { Country.BD }),
    BGN(975, Digits.DIGITS_2, "Bulgarian lev", new Country[] { Country.BG }),
    BHD(48,  Digits.DIGITS_3, "Bahraini dinar", new Country[] { Country.BH }),
    BIF(108, Digits.DIGITS_0, "Burundian franc", new Country[] { Country.BI }),
    BMD(60,  Digits.DIGITS_2, "Bermudian dollar", new Country[] { Country.BM }),
    BND(96,  Digits.DIGITS_2, "Brunei dollar", new Country[] { Country.BN, Country.SG }),
    BOB(68,  Digits.DIGITS_2, "Boliviano", new Country[] { Country.BO }),
    BOV(984, Digits.DIGITS_2, "Bolivian Mvdol (funds code)", new Country[] { Country.BO }),
    BRL(986, Digits.DIGITS_2, "Brazilian real", new Country[] { Country.BR }),
    BSD(44,  Digits.DIGITS_2, "Bahamian dollar", new Country[] { Country.BS }),
    BTN(64,  Digits.DIGITS_2, "Bhutanese ngultrum", new Country[] { Country.BT }),
    BWP(72,  Digits.DIGITS_2, "Botswana pula", new Country[] { Country.BW }),
    BYR(974, Digits.DIGITS_0, "Belarusian ruble", new Country[] { Country.BY }),
    BZD(84,  Digits.DIGITS_2, "Belize dollar", new Country[] { Country.BZ }),
    CAD(124, Digits.DIGITS_2, "Canadian dollar", new Country[] { Country.CA }),
    CDF(976, Digits.DIGITS_2, "Congolese franc", new Country[] { Country.CD }),
    CHE(947, Digits.DIGITS_2, "WIR Euro (complementary currency)", new Country[] { Country.CH }),
    CHF(756, Digits.DIGITS_2, "Swiss franc", new Country[] { Country.CH, Country.LI }),
    CHW(948, Digits.DIGITS_2, "WIR Franc (complementary currency)", new Country[] { Country.CH }),
    CLF(990, Digits.DIGITS_0, "Unidad de Fomento (funds code)", new Country[] { Country.CL }),
    CLP(152, Digits.DIGITS_0, "Chilean peso", new Country[] { Country.CL }),
    CNY(156, Digits.DIGITS_2, "Chinese yuan", new Country[] { Country.CN }),
    COP(170, Digits.DIGITS_2, "Colombian peso", new Country[] { Country.CO }),
    COU(970, Digits.DIGITS_2, "Unidad de Valor Real", new Country[] { Country.CO }),
    CRC(188, Digits.DIGITS_2, "Costa Rican colon", new Country[] { Country.CR }),
    CUC(931, Digits.DIGITS_2, "Cuban convertible peso", new Country[] { Country.CU }),
    CUP(192, Digits.DIGITS_2, "Cuban peso", new Country[] { Country.CU }),
    CVE(132, Digits.DIGITS_0, "Cape Verde escudo", new Country[] { Country.CV }),
    CZK(203, Digits.DIGITS_2, "Czech koruna", new Country[] { Country.CZ }),
    DJF(262, Digits.DIGITS_0, "Djiboutian franc", new Country[] { Country.DJ }),
    DKK(208, Digits.DIGITS_2, "Danish krone", new Country[] { Country.DK, Country.FO, Country.GL }),
    DOP(214, Digits.DIGITS_2, "Dominican peso", new Country[] { Country.DO }),
    DZD(12,  Digits.DIGITS_2, "Algerian dinar", new Country[] { Country.DZ }),
    EGP(818, Digits.DIGITS_2, "Egyptian pound", new Country[] { Country.EG }),
    ERN(232, Digits.DIGITS_2, "Eritrean nakfa", new Country[] { Country.ER }),
    ETB(230, Digits.DIGITS_2, "Ethiopian birr", new Country[] { Country.ET }),
    EUR(978, Digits.DIGITS_2, "Euro", new Country[] { Country.AD, Country.AT, Country.BE, Country.CY, Country.EE, Country.FI, Country.FR, Country.DE, Country.GR, Country.IE, Country.IT, Country.LU, Country.MT, Country.MC, Country.ME, Country.NL, Country.PT, Country.SM, Country.SK, Country.SI, Country.ES, Country.VA }),
    FJD(242, Digits.DIGITS_2, "Fiji dollar", new Country[] { Country.FJ }),
    FKP(238, Digits.DIGITS_2, "Falkland Islands pound", new Country[] { Country.FK }),
    GBP(826, Digits.DIGITS_2, "Pound sterling", new Country[] { Country.GB, Country.IM, Country.GS, Country.IO  }),
    GEL(981, Digits.DIGITS_2, "Georgian lari", new Country[] { Country.GE }),
    GHS(936, Digits.DIGITS_2, "Ghanaian cedi", new Country[] { Country.GH }),
    GIP(292, Digits.DIGITS_2, "Gibraltar pound", new Country[] { Country.GI }),
    GMD(270, Digits.DIGITS_2, "Gambian dalasi", new Country[] { Country.GM }),
    GNF(324, Digits.DIGITS_0, "Guinean franc", new Country[] { Country.GN }),
    GTQ(320, Digits.DIGITS_2, "Guatemalan quetzal", new Country[] { Country.GT }),
    GYD(328, Digits.DIGITS_2, "Guyanese dollar", new Country[] { Country.GY }),
    HKD(344, Digits.DIGITS_2, "Hong Kong dollar", new Country[] { Country.HK, Country.MO }),
    HNL(340, Digits.DIGITS_2, "Honduran lempira", new Country[] { Country.HN }),
    HRK(191, Digits.DIGITS_2, "Croatian kuna", new Country[] { Country.HR }),
    HTG(332, Digits.DIGITS_2, "Haitian gourde", new Country[] { Country.HT }),
    HUF(348, Digits.DIGITS_2, "Hungarian forint", new Country[] { Country.HU }),
    IDR(360, Digits.DIGITS_2, "Indonesian rupiah", new Country[] { Country.ID }),
    ILS(376, Digits.DIGITS_2, "Israeli new shekel", new Country[] { Country.IL, Country.PS }),
    INR(356, Digits.DIGITS_2, "Indian rupee", new Country[] { Country.IN }),
    IQD(368, Digits.DIGITS_3, "Iraqi dinar", new Country[] { Country.IQ }),
    IRR(364, Digits.DIGITS_0, "Iranian rial", new Country[] { Country.IR }),
    ISK(352, Digits.DIGITS_0, "Icelandic króna", new Country[] { Country.IS }),
    JMD(388, Digits.DIGITS_2, "Jamaican dollar", new Country[] { Country.JM }),
    JOD(400, Digits.DIGITS_3, "Jordanian dinar", new Country[] { Country.JO }),
    JPY(392, Digits.DIGITS_0, "Japanese yen", new Country[] { Country.JP }),
    KES(404, Digits.DIGITS_2, "Kenyan shilling", new Country[] { Country.KE }),
    KGS(417, Digits.DIGITS_2, "Kyrgyzstani som", new Country[] { Country.KG }),
    KHR(116, Digits.DIGITS_2, "Cambodian riel", new Country[] { Country.KH }),
    KMF(174, Digits.DIGITS_0, "Comoro franc", new Country[] { Country.KM }),
    KPW(408, Digits.DIGITS_0, "North Korean won", new Country[] { Country.KP }),
    KRW(410, Digits.DIGITS_0, "South Korean won", new Country[] { Country.KR }),
    KWD(414, Digits.DIGITS_3, "Kuwaiti dinar", new Country[] { Country.KW }),
    KYD(136, Digits.DIGITS_2, "Cayman Islands dollar", new Country[] { Country.KY }),
    KZT(398, Digits.DIGITS_2, "Kazakhstani tenge", new Country[] { Country.KZ }),
    LAK(418, Digits.DIGITS_0, "Lao kip", new Country[] { Country.LA }),
    LBP(422, Digits.DIGITS_0, "Lebanese pound", new Country[] { Country.LB }),
    LKR(144, Digits.DIGITS_2, "Sri Lankan rupee", new Country[] { Country.LK }),
    LRD(430, Digits.DIGITS_2, "Liberian dollar", new Country[] { Country.LR }),
    LSL(426, Digits.DIGITS_2, "Lesotho loti", new Country[] { Country.LS }),
    LTL(440, Digits.DIGITS_2, "Lithuanian litas", new Country[] { Country.LT }),
    LVL(428, Digits.DIGITS_2, "Latvian lats", new Country[] { Country.LV }),
    LYD(434, Digits.DIGITS_3, "Libyan dinar", new Country[] { Country.LY }),
    MAD(504, Digits.DIGITS_2, "Moroccan dirham", new Country[] { Country.MA }),
    MDL(498, Digits.DIGITS_2, "Moldovan leu", new Country[] { Country.MD }),
    MGA(969, Digits.DIGITS_07, "Malagasy ariary", new Country[] { Country.MG }),
    MKD(807, Digits.DIGITS_0, "Macedonian denar", new Country[] { Country.MK }),
    MMK(104, Digits.DIGITS_0, "Myanma kyat", new Country[] { Country.MM }),
    MNT(496, Digits.DIGITS_2, "Mongolian tugrik", new Country[] { Country.MN }),
    MOP(446, Digits.DIGITS_2, "Macanese pataca", new Country[] { Country.MO }),
    MRO(478, Digits.DIGITS_07, "Mauritanian ouguiya", new Country[] { Country.MR }),
    MUR(480, Digits.DIGITS_2, "Mauritian rupee", new Country[] { Country.MU }),
    MVR(462, Digits.DIGITS_2, "Maldivian rufiyaa", new Country[] { Country.MV }),
    MWK(454, Digits.DIGITS_2, "Malawian kwacha", new Country[] { Country.MW }),
    MXN(484, Digits.DIGITS_2, "Mexican peso", new Country[] { Country.MX }),
    MXV(979, Digits.DIGITS_2, "Mexican Unidad de Inversion (UDI) (funds code)", new Country[] { Country.MX }),
    MYR(458, Digits.DIGITS_2, "Malaysian ringgit", new Country[] { Country.MY }),
    MZN(943, Digits.DIGITS_2, "Mozambican metical", new Country[] { Country.MZ }),
    NAD(516, Digits.DIGITS_2, "Namibian dollar", new Country[] { Country.NA }),
    NGN(566, Digits.DIGITS_2, "Nigerian naira", new Country[] { Country.NG }),
    NIO(558, Digits.DIGITS_2, "Nicaraguan córdoba", new Country[] { Country.NI }),
    NOK(578, Digits.DIGITS_2, "Norwegian krone", new Country[] { Country.NO, Country.SJ, Country.BV }),
    NPR(524, Digits.DIGITS_2, "Nepalese rupee", new Country[] { Country.NP }),
    NZD(554, Digits.DIGITS_2, "New Zealand dollar", new Country[] { Country.CK, Country.NZ, Country.NU, Country.PN, Country.TK }),
    OMR(512, Digits.DIGITS_3, "Omani rial", new Country[] { Country.OM }),
    PAB(590, Digits.DIGITS_2, "Panamanian balboa", new Country[] { Country.PA }),
    PEN(604, Digits.DIGITS_2, "Peruvian nuevo sol", new Country[] { Country.PE }),
    PGK(598, Digits.DIGITS_2, "Papua New Guinean kina", new Country[] { Country.PG }),
    PHP(608, Digits.DIGITS_2, "Philippine peso", new Country[] { Country.PH }),
    PKR(586, Digits.DIGITS_2, "Pakistani rupee", new Country[] { Country.PK }),
    PLN(985, Digits.DIGITS_2, "Polish złoty", new Country[] { Country.PL }),
    PYG(600, Digits.DIGITS_0, "Paraguayan guaraní", new Country[] { Country.PY }),
    QAR(634, Digits.DIGITS_2, "Qatari riyal", new Country[] { Country.QA }),
    RON(946, Digits.DIGITS_2, "Romanian new leu", new Country[] { Country.RO }),
    RSD(941, Digits.DIGITS_2, "Serbian dinar", new Country[] { Country.RS }),
    RUB(643, Digits.DIGITS_2, "Russian rouble", new Country[] { Country.RU }),
    RWF(646, Digits.DIGITS_0, "Rwandan franc", new Country[] { Country.RW }),
    SAR(682, Digits.DIGITS_2, "Saudi riyal", new Country[] { Country.SA }),
    SBD(90,  Digits.DIGITS_2, "Solomon Islands dollar", new Country[] { Country.SB }),
    SCR(690, Digits.DIGITS_2, "Seychelles rupee", new Country[] { Country.SC }),
    SDG(938, Digits.DIGITS_2, "Sudanese pound", new Country[] { Country.SD }),
    SEK(752, Digits.DIGITS_2, "Swedish krona/kronor", new Country[] { Country.SE }),
    SGD(702, Digits.DIGITS_2, "Singapore dollar", new Country[] { Country.SG, Country.BN }),
    SHP(654, Digits.DIGITS_2, "Saint Helena pound", new Country[] { Country.SH }),
    SLL(694, Digits.DIGITS_0, "Sierra Leonean leone", new Country[] { Country.SL }),
    SOS(706, Digits.DIGITS_2, "Somali shilling", new Country[] { Country.SO }),
    SRD(968, Digits.DIGITS_2, "Surinamese dollar", new Country[] { Country.SR }),
    SSP(728, Digits.DIGITS_2, "South Sudanese pound", new Country[] { Country.SS }),
    STD(678, Digits.DIGITS_0, "São Tomé and Príncipe dobra", new Country[] { Country.ST }),
    SYP(760, Digits.DIGITS_2, "Syrian pound", new Country[] { Country.SY }),
    SZL(748, Digits.DIGITS_2, "Swazi lilangeni", new Country[] { Country.SZ }),
    THB(764, Digits.DIGITS_2, "Thai baht", new Country[] { Country.TH }),
    TJS(972, Digits.DIGITS_2, "Tajikistani somoni", new Country[] { Country.TJ }),
    TMT(934, Digits.DIGITS_2, "Turkmenistani manat", new Country[] { Country.TM }),
    TND(788, Digits.DIGITS_3, "Tunisian dinar", new Country[] { Country.TN }),
    TOP(776, Digits.DIGITS_2, "Tongan paʻanga", new Country[] { Country.TO }),
    TRY(949, Digits.DIGITS_2, "Turkish lira", new Country[] { Country.TR }),
    TTD(780, Digits.DIGITS_2, "Trinidad and Tobago dollar", new Country[] { Country.TT }),
    TWD(901, Digits.DIGITS_2, "New Taiwan dollar", new Country[] { Country.TW }),
    TZS(834, Digits.DIGITS_2, "Tanzanian shilling", new Country[] { Country.TZ }),
    UAH(980, Digits.DIGITS_2, "Ukrainian hryvnia", new Country[] { Country.UA }),
    UGX(800, Digits.DIGITS_2, "Ugandan shilling", new Country[] { Country.UG }),
    USD(840, Digits.DIGITS_2, "United States dollar", new Country[] { Country.AS, Country.BB, Country.BM, Country.IO, Country.VG, Country.BQ, Country.EC, Country.SV, Country.GU, Country.HT, Country.MH, Country.FM, Country.MP, Country.PW, Country.PA, Country.PR, Country.TL, Country.TC, Country.US, Country.VI, Country.ZW }),
    USN(997, Digits.DIGITS_2, "United States dollar (next day) (funds code)", new Country[] { Country.US }),
    USS(998, Digits.DIGITS_2, "United States dollar (same day) (funds code)", new Country[] { Country.US }),
    UYI(940, Digits.DIGITS_0, "Uruguay Peso en Unidades Indexadas (URUIURUI) (funds code)", new Country[] { Country.UY }),
    UYU(858, Digits.DIGITS_2, "Uruguayan peso", new Country[] { Country.UY }),
    UZS(860, Digits.DIGITS_2, "Uzbekistan som", new Country[] { Country.UZ }),
    VEF(937, Digits.DIGITS_2, "Venezuelan bolívar fuerte", new Country[] { Country.VE }),
    VND(704, Digits.DIGITS_0, "Vietnamese dong", new Country[] { Country.VN }),
    VUV(548, Digits.DIGITS_0, "Vanuatu vatu", new Country[] { Country.VU }),
    WST(882, Digits.DIGITS_2, "Samoan tala", new Country[] { Country.WS }),
    XAF(950, Digits.DIGITS_0, "CFA franc BEAC", new Country[] { Country.CM, Country.CF, Country.CD, Country.TD, Country.GQ, Country.GA }),
    XAG(961, Digits.DIGITS_NO, "Silver (one troy ounce)", new Country[] { }),
    XAU(959, Digits.DIGITS_NO, "Gold (one troy ounce)", new Country[] { }),
    XBA(955, Digits.DIGITS_NO, "European Composite Unit (EURCO) (bond market unit)", new Country[] { }),
    XBB(956, Digits.DIGITS_NO, "European Monetary Unit (E.M.U.-6) (bond market unit)", new Country[] { }),
    XBC(957, Digits.DIGITS_NO, "European Unit of Account 9 (E.U.A.-9) (bond market unit)", new Country[] { }),
    XBD(958, Digits.DIGITS_NO, "European Unit of Account 17 (E.U.A.-17) (bond market unit)", new Country[] { }),
    XCD(951, Digits.DIGITS_2, "East Caribbean dollar", new Country[] { Country.AI, Country.AG, Country.DM, Country.GD, Country.MS, Country.KN, Country.LC, Country.VC }),
    XDR(960, Digits.DIGITS_NO, "Special drawing rights  International Monetary Fund", new Country[] { }),
    XFU(-1,  Digits.DIGITS_NO, "UIC franc (special settlement currency)", new Country[] { }),
    XOF(952, Digits.DIGITS_0, "CFA franc BCEAO", new Country[] { Country.BJ, Country.BF, Country.CI, Country.GW, Country.ML, Country.NE, Country.SN, Country.TG }),
    XPD(964, Digits.DIGITS_NO, "Palladium (one troy ounce)", new Country[] { }),
    XPF(953, Digits.DIGITS_0, "CFP franc", new Country[] { Country.PF, Country.NC, Country.WF }),
    XPT(962, Digits.DIGITS_NO, "Platinum (one troy ounce)", new Country[] { }),
    XTS(963, Digits.DIGITS_NO, "Code reserved for testing purposes", new Country[] { }),
    XXX(999, Digits.DIGITS_NO, "No currency", new Country[] { }),
    YER(886, Digits.DIGITS_2, "Yemeni rial", new Country[] { Country.YE }),
    ZAR(710, Digits.DIGITS_2, "South African rand", new Country[] { Country.ZA }),
    ZMK(894, Digits.DIGITS_2, "Zambian kwacha", new Country[] { Country.ZM });


    private final String code;
    private final String name;
    private final int numeric;
    private final Digits digits;
    private final Country[] countries;

    private enum Digits { DIGITS_0, DIGITS_2, DIGITS_3, DIGITS_07, DIGITS_NO };

    private Currency(int numeric, Digits digits, String name, Country[] countries) {
        this.code = name().toUpperCase();
        this.name = name;
        this.numeric = numeric;
        this.digits = digits;
        this.countries = countries;
    }

    /**
     * Returns the currency code.
     *
     * @return  The code, e.g. "USD", "EUR", etc.
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the currency name in English.
     *
     * @return  The English currency name, e.g. "United States dollar".
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a list of countries that use the currency.
     *
     * @return  The countries that use the currency.
     */
    public Country[] getCountries() {
        return countries;
    }

    /**
     * Formats and outputs the amount for the given currency.
     *
     * @param amount  The amount.
     * @return  The formatted amount, e.g. "USD 10.38".
     */
    public String format(long amount) {
        String formatted;
        switch (digits) {
            case DIGITS_0: {
                // e.g. "10"
                formatted = String.valueOf(amount);
                break;
            }
            case DIGITS_2: {
                // e.g. "10.99"
                String a = String.valueOf(amount / 100);
                String b = String.valueOf(amount % 100);
                while (b.length() < 2) {
                    b = "0" + b;
                }
                formatted = a + "." + b;
                break;
            }
            case DIGITS_3: {
                // e.g. "10.999"
                String a = String.valueOf(amount / 1000);
                String b = String.valueOf(amount % 1000);
                while (b.length() < 3) {
                    b = "0" + b;
                }
                formatted = a + "." + b;
                break;
            }
            case DIGITS_07: {
                // e.g. "10" ==> NOTE: http://en.wikipedia.org/wiki/Malagasy_ariary (some special rules apply!?)
                formatted = String.valueOf(amount);
                break;
            }
            case DIGITS_NO: {
                formatted = String.valueOf(amount);
                break;
            }
            default:
                System.err.println("Digits not implemented: " + digits);
                formatted = String.valueOf(amount);
        }
        return getCode() + " " + formatted;
    }

    /**
     * Returns the currency for the given code.
     *
     * @param code  The code, e.g. "USD", "EUR", etc.
     * @return  The corresponding currency or null if it doesn't exist.
     */
    public static Currency find(String code) {
        for (int i = 0; i < values().length; i++) {
            if (values()[i].getCode().equals(code)) {
                return values()[i];
            }
        }

        // not found
        return null;
    }

    /**
     * Returns the currency for the given code.
     *
     * @param numeric  The code, e.g. "USD", "EUR", etc.
     * @return  The corresponding currency or null if it doesn't exist.
     */
    public static Currency findByNumeric(int numeric) {
        for (int i = 0; i < values().length; i++) {
            if (values()[i].getISOCodeNumeric() == numeric) {
                return values()[i];
            }
        }

        // not found
        return null;
    }

    /**
     * Returns the currency for the inputed country.
     *
     * @param country  The country.
     * @return  The currency or null if multiply currencies or no currencies exist.
     */
    public static Currency find(Country country) {
        // use some default rules for countries with multiple currencies (should go into Country.java!)
        switch (country) {
            case BO: {
                return Currency.BOB;
            }
            case CH: {
                return Currency.CHF;
            }
            case CL: {
                return Currency.CLP;
            }
            case MX: {
                return Currency.MXN;
            }
            case US: {
                return Currency.USD;
            }
            case UY: {
                return Currency.UYU;
            }
            default: {
                // if default rules don't apply, let's see if we can resolve otherwise!
                Currency currency = null;
                for (int i = 0; i < values().length; i++) {
                    Country[] countries = values()[i].getCountries();
                    for (int k = 0; k < countries.length; k++) {
                        if (countries[k] == country) {
                            if (currency != null) {
                                // more than one currency!
                                return null;
                            }
                            else {
                                currency = values()[i];
                            }
                        }
                    }
                }

                // return the currency
                return currency;
            }
        }
    }

    /**
     * Returns the currency for the inputed country.
     *
     * @param country  The country.
     * @param defaultCurrency  The default currency to use if more than 1 currency exists or no currency exists.
     * @return  The currency or default currency if multiply currencies or no currencies exist.
     */
    public static Currency find(Country country, Currency defaultCurrency) {
        Currency currency = find(country);
        if (currency == null) {
            return defaultCurrency;
        }
        else {
            return currency;
        }
    }

    // -------------------------------------------------------------------------------------------------------------------

    public String getISOCodeAlpha() {
        return name();
    }

    public int getISOCodeNumeric() {
        return numeric;
    }

    @Override
    public String toString() {
        return code;
    }
}

