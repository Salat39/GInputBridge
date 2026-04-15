package com.salat.gbinder.car.domain.util

import com.salat.gbinder.car.domain.entity.PropertyData

fun getAllProperty(): List<PropertyData> = listOf(
    PropertyData(
        alias = "IBcm.BCM_FUNC_ALL_READING_LIGHTS_SWITCH",
        type = 2,
        key = "BCM_FUNC_ALL_READING_LIGHTS_SWITCH",
        value = 554763008,
        description = "Включение/выключение всех плафонов освещения салона.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_CHARGING_CAP",
        type = 2,
        key = "BCM_FUNC_CHARGING_CAP",
        value = 553780480,
        description = "Электропривод крышки зарядного порта.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_CHILD_SAFETY_LOCK",
        type = 2,
        key = "BCM_FUNC_CHILD_SAFETY_LOCK",
        value = 553780224,
        description = "Детский замок задних дверей.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_CUSTOM_KEY",
        type = 2,
        key = "BCM_FUNC_CUSTOM_KEY",
        value = 554762496,
        description = "Назначение функции на пользовательскую кнопку.",
        possibleValues = mapOf(
            "CUSTOM_KEY_TYPE_360_PANORAMA" to 1,
            "CUSTOM_KEY_TYPE_AUTO_PARK" to 101,
            "CUSTOM_KEY_TYPE_COLLECT_FAV" to 5,
            "CUSTOM_KEY_TYPE_DIM_FULL_SCREEN_MAP" to 3,
            "CUSTOM_KEY_TYPE_DRIVING_MODE" to 102,
            "CUSTOM_KEY_TYPE_DVR" to 0,
            "CUSTOM_KEY_TYPE_LOUD_SPEAKER" to 99,
            "CUSTOM_KEY_TYPE_NAVIGATION" to 2,
            "CUSTOM_KEY_TYPE_REAR_MIRROR_ADJUST" to 6,
            "CUSTOM_KEY_TYPE_SOUND_SWITCH" to 4,
            "CUSTOM_KEY_TYPE_UNLCKTRUNK" to 100,
        )
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_DIM_ZONE_A_WARNING",
        type = 2,
        key = "BCM_FUNC_DIM_ZONE_A_WARNING",
        value = 555746816,
        description = "Предупреждение о затемнённой зоне A.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_DISPLAY_ONOFF",
        type = 2,
        key = "BCM_FUNC_DISPLAY_ONOFF",
        value = 554697216,
        description = "Питание подсистем/подсветки кузова: вкл/выкл.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_DOOR",
        type = 2,
        key = "BCM_FUNC_DOOR",
        value = 553779456,
        description = "Команды электропривода двери (открыть/закрыть/пауза).",
        possibleValues = mapOf(
            "DOOR_CLOSE" to 0,
            "DOOR_OPEN" to 1,
            "DOOR_PAUSE" to 553779457,
        )
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_DOOR_LOCK",
        type = 2,
        key = "BCM_FUNC_DOOR_LOCK",
        value = 553779712,
        description = "Запирание дверей.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_DOOR_LOCK_FAULT",
        type = 2,
        key = "BCM_FUNC_DOOR_LOCK_FAULT",
        value = 553713920,
        description = "Неисправность привода замков дверей.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_DOOR_STATUS",
        type = 2,
        key = "BCM_FUNC_DOOR_STATUS",
        value = 553785856,
        description = "Состояние дверей (открыто/закрыто).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_FOLD_REAR_MIRROR",
        type = 2,
        key = "BCM_FUNC_FOLD_REAR_MIRROR",
        value = 554041600,
        description = "Складирование наружных зеркал.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_FPL_FOLLOW_DRL",
        type = 2,
        key = "BCM_FUNC_FPL_FOLLOW_DRL",
        value = 555745536,
        description = "Связь ближнего света с ДХО (сценарии).",
        possibleValues = mapOf(
            "FPL_FOLLOW_DRL_MODE1" to 555745537,
            "FPL_FOLLOW_DRL_MODE2" to 555745538,
        )
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_FUEL_CAP",
        type = 2,
        key = "BCM_FUNC_FUEL_CAP",
        value = 553780736,
        description = "Электропривод лючка топливного бака.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_LIGHT_ATMOSPHERE_LAMPS",
        type = 2,
        key = "BCM_FUNC_LIGHT_ATMOSPHERE_LAMPS",
        value = 553979904,
        description = "Атмосферная подсветка салона.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_LIGHT_FRONT_FOG_LAMPS",
        type = 2,
        key = "BCM_FUNC_LIGHT_FRONT_FOG_LAMPS",
        value = 553976832,
        description = "Передние противотуманные фары: вкл/выкл.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_LIGHT_HAZARD_FLASHERS",
        type = 2,
        key = "BCM_FUNC_LIGHT_HAZARD_FLASHERS",
        value = 553979648,
        description = "Аварийная сигнализация: вкл/выкл.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_LIGHT_LEFT_TRUN_SIGNAL",
        type = 2,
        key = "BCM_FUNC_LIGHT_LEFT_TRUN_SIGNAL",
        value = 553980160,
        description = "Левый указатель поворота.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_LIGHT_READING_LIGHT",
        type = 2,
        key = "BCM_FUNC_LIGHT_READING_LIGHT",
        value = 553980672,
        description = "Индивидуальный плафон освещения салона.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_LIGHT_REAR_FOG_LAMPS",
        type = 2,
        key = "BCM_FUNC_LIGHT_REAR_FOG_LAMPS",
        value = 553977088,
        description = "Задние противотуманные фонари: вкл/выкл.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_LIGHT_RIGHT_TRUN_SIGNAL",
        type = 2,
        key = "BCM_FUNC_LIGHT_RIGHT_TRUN_SIGNAL",
        value = 553980416,
        description = "Правый указатель поворота.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_LIGHT_WELCOME_LIGHT",
        type = 2,
        key = "BCM_FUNC_LIGHT_WELCOME_LIGHT",
        value = 553981952,
        description = "Приветственная подсветка при подходе/отпирании.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_POWER_ONOFF",
        type = 2,
        key = "BCM_FUNC_POWER_ONOFF",
        value = 554696960,
        description = "Главный выключатель питания BCM‑подсистем.",
        possibleValues = mapOf(
            "BCM_FUNC_POWER_ONOFF_CONFIRM" to 554696962,
        )
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_REAR_MIRROR_ADJUST",
        type = 2,
        key = "BCM_FUNC_REAR_MIRROR_ADJUST",
        value = 554041856,
        description = "Электрорегулировка наружных зеркал.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_STEERING_WHEEL_ADJUST",
        type = 2,
        key = "BCM_FUNC_STEERING_WHEEL_ADJUST",
        value = 554107136,
        description = "Электрорегулировка рулевой колонки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_SUNCURT_OPEN_BTN",
        type = 2,
        key = "BCM_FUNC_SUNCURT_CLS_BTN",
        value = 555746560,
        description = "Кнопка закрытия солнцезащитной шторки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_SUNCURT_OPEN_BTN",
        type = 2,
        key = "BCM_FUNC_SUNCURT_OPEN_BTN",
        value = 555746304,
        description = "Кнопка открытия солнцезащитной шторки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_SUNROOF_CLS_BTN",
        type = 2,
        key = "BCM_FUNC_SUNROOF_CLS_BTN",
        value = 555746048,
        description = "Кнопка закрытия люка.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_SUNROOF_ININ_SWITCH",
        type = 2,
        key = "BCM_FUNC_SUNROOF_ININ_SWITCH",
        value = 555745280,
        description = "Переключатель направления люка (вперёд/назад).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_SUNROOF_OPEN_BTN",
        type = 2,
        key = "BCM_FUNC_SUNROOF_OPEN_BTN",
        value = 555745792,
        description = "Кнопка открытия люка.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_SUNROOF_TILT",
        type = 2,
        key = "BCM_FUNC_SUNROOF_TILT",
        value = 553845760,
        description = "Режим приоткрытия (TILT) люка.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_WASHER",
        type = 2,
        key = "BCM_FUNC_WASHER",
        value = 553910528,
        description = "Омыватель ветрового стекла.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_WINDOW",
        type = 2,
        key = "BCM_FUNC_WINDOW",
        value = 553844992,
        description = "Команды стеклоподъёмника (откр./закр./пауза/процент).",
        possibleValues = mapOf(
            "WINDOW_PAUSE" to 553844993,
            "WINDOW_HALF" to 553844994,
            "WINDOW_OPEN_PAUSE" to 553844995,
            "WINDOW_CLOSE_PAUSE" to 553844996,
            "WINDOW_CLOSE" to 0,
            "WINDOW_OPEN" to 1,
            "WINDOW_MIN" to 0,
            "WINDOW_MAX" to 100,
        )
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_WINDOW_CURRENT_POS",
        type = 2,
        key = "BCM_FUNC_WINDOW_CURRENT_POS",
        value = 553846272,
        description = "Текущее положение стеклоподъёмника, %.",
        possibleValues = mapOf(
            "WINDOW_CLOSE" to 0,
            "WINDOW_OPEN" to 1,
            "WINDOW_MIN" to 0,
            "WINDOW_MAX" to 100,
        )
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_WINDOW_MOVING_STATE",
        type = 2,
        key = "BCM_FUNC_WINDOW_MOVING_STATE",
        value = 554762752,
        description = "Состояние движения стеклоподъёмника.",
        possibleValues = mapOf(
            "WINDOW_CLOSE" to 0,
            "WINDOW_OPEN" to 1,
            "WINDOW_MIN" to 0,
            "WINDOW_MAX" to 100,
        )
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_WINDOW_POS",
        type = 2,
        key = "BCM_FUNC_WINDOW_POS",
        value = 553845504,
        description = "Целевая позиция стеклоподъёмника, %.",
        possibleValues = mapOf(
            "WINDOW_CLOSE" to 0,
            "WINDOW_OPEN" to 1,
            "WINDOW_MIN" to 0,
            "WINDOW_MAX" to 100,
        )
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_WIPER",
        type = 2,
        key = "BCM_FUNC_WIPER",
        value = 553713920,
        description = "Управление дворниками (режим/скорость).",
        possibleValues = mapOf(
            "WIPER_GEAR_AUTO" to 553713921,
            "WIPER_GEAR_LOW" to 553713922,
            "WIPER_GEAR_HIGHT" to 553713923,
            "WIPER_GEAR_INTERMITTENT" to 553713924,
            "WIPER_GEAR_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "ICarFunction.CAR_MODULE_LAMP",
        type = 2,
        key = "CAR_MODULE_LAMP",
        value = 721420288,
        description = "Выбор режима наружного света (OFF/габариты/ближний/авто/AHBC).",
        possibleValues = mapOf(
            "LAMP_EXTERIOR_LIGHT_CONTROL_AHBC" to 537136644,
            "LAMP_EXTERIOR_LIGHT_CONTROL_AUTOMATIC" to 537136643,
            "LAMP_EXTERIOR_LIGHT_CONTROL_LOWBEAM" to 537136642,
            "LAMP_EXTERIOR_LIGHT_CONTROL_OFF" to 0,
            "LAMP_EXTERIOR_LIGHT_CONTROL_POS_LIGHT" to 537136641,
        )
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_BATTERY_CHARGING_CURRENT_POWER",
        type = 2,
        key = "CHARGE_FUNC_BATTERY_CHARGING_CURRENT_POWER",
        value = 606080000,
        description = "Текущая мощность зарядки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_BATTERY_DISCHARGING_CURRENT_POWER",
        type = 2,
        key = "CHARGE_FUNC_BATTERY_DISCHARGING_CURRENT_POWER",
        value = 606080256,
        description = "Текущая мощность раздачи энергии (V2L/V2V).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_CHARGE_IMMEDIATELY",
        type = 2,
        key = "CHARGE_FUNC_CHARGE_IMMEDIATELY",
        value = 609222656,
        description = "Немедленный старт зарядки (обход расписания).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_CHARGING",
        type = 2,
        key = "CHARGE_FUNC_CHARGING",
        value = 605028608,
        description = "Статусы процесса зарядки/подключения.",
        possibleValues = mapOf(
            "CHARGING_PLUG_STATE_CONNECTED_WAITING" to 605225493,
            "CHARGING_PLUG_STATE_DISCONNECTED" to 605225489,
            "CHARGING_PLUG_STATE_DIS_CHRGN_CONNECTED" to 605225492,
            "CHARGING_PLUG_STATE_FAULT" to 605225495,
            "CHARGING_PLUG_STATE_NONE" to 605225496,
            "CHARGING_PLUG_STATE_QUICK_CHRGN_CONNECTED" to 605225491,
            "CHARGING_PLUG_STATE_SLOW_CHRGN_CONNECTED" to 605225490,
            "CHARGING_PLUG_STATE_WRONG_OPERATION" to 605225494,
        )
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_CHARGING_CURRENT",
        type = 2,
        key = "CHARGE_FUNC_CHARGING_CURRENT",
        value = 605029888,
        description = "Текущий зарядный ток.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_CHARGING_CURRENT_MAX",
        type = 2,
        key = "CHARGE_FUNC_CHARGING_CURRENT_MAX",
        value = 605030144,
        description = "Максимально допустимый зарядный ток.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_CHARGING_CURRENT_MIN",
        type = 2,
        key = "CHARGE_FUNC_CHARGING_CURRENT_MIN",
        value = 605030400,
        description = "Минимально допустимый зарядный ток.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_CHARGING_CURRENT_STEP",
        type = 2,
        key = "CHARGE_FUNC_CHARGING_CURRENT_STEP",
        value = 605030656,
        description = "Шаг регулировки зарядного тока.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_CHARGING_ENERGY",
        type = 2,
        key = "CHARGE_FUNC_CHARGING_ENERGY",
        value = 605291776,
        description = "Энергия, переданная за текущую сессию зарядки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_CHARGING_ESTIMATED_TIME",
        type = 2,
        key = "CHARGE_FUNC_CHARGING_ESTIMATED_TIME",
        value = 605291264,
        description = "Расчётное время до завершения AC‑зарядки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_CHARGING_ESTIMATED_TIME_DC",
        type = 2,
        key = "CHARGE_FUNC_CHARGING_ESTIMATED_TIME_DC",
        value = 605292032,
        description = "Расчётное время до завершения DC‑зарядки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_CHARGING_PLUG_STATE",
        type = 2,
        key = "CHARGE_FUNC_CHARGING_PLUG_STATE",
        value = 605225472,
        description = "Состояние штекера зарядного разъёма.",
        possibleValues = mapOf(
            "CHARGING_PLUG_STATE_CONNECTED_WAITING" to 605225493,
            "CHARGING_PLUG_STATE_DISCONNECTED" to 605225489,
            "CHARGING_PLUG_STATE_DIS_CHRGN_CONNECTED" to 605225492,
            "CHARGING_PLUG_STATE_FAULT" to 605225495,
            "CHARGING_PLUG_STATE_NONE" to 605225496,
            "CHARGING_PLUG_STATE_QUICK_CHRGN_CONNECTED" to 605225491,
            "CHARGING_PLUG_STATE_SLOW_CHRGN_CONNECTED" to 605225490,
            "CHARGING_PLUG_STATE_WRONG_OPERATION" to 605225494,
        )
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_CHARGING_PLUG_TYPE",
        type = 2,
        key = "CHARGE_FUNC_CHARGING_PLUG_TYPE",
        value = 605225216,
        description = "Тип подключённого зарядного штекера.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_CHARGING_SOC",
        type = 2,
        key = "CHARGE_FUNC_CHARGING_SOC",
        value = 605028864,
        description = "Уровень заряда HV‑батареи (SoC) при зарядке.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_CHARGING_SOC_MAX",
        type = 2,
        key = "CHARGE_FUNC_CHARGING_SOC_MAX",
        value = 605029120,
        description = "Целевой максимум SoC при зарядке.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_CHARGING_SOC_MIN",
        type = 2,
        key = "CHARGE_FUNC_CHARGING_SOC_MIN",
        value = 605029376,
        description = "Минимальный SoC для зарядки/целевой порог.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_CHARGING_SOC_STEP",
        type = 2,
        key = "CHARGE_FUNC_CHARGING_SOC_STEP",
        value = 605029632,
        description = "Шаг изменения целевого SoC.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_CHARGING_WORK_CURRENT",
        type = 2,
        key = "CHARGE_FUNC_CHARGING_WORK_CURRENT",
        value = 605291008,
        description = "Ток на разъёме во время зарядки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_CHARGING_WORK_VOLTAGE",
        type = 2,
        key = "CHARGE_FUNC_CHARGING_WORK_VOLTAGE",
        value = 605290752,
        description = "Напряжение на разъёме во время зарядки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_DISCHARGING_ENETGY",
        type = 2,
        key = "CHARGE_FUNC_DISCHARGING_ENETGY",
        value = 605357056,
        description = "Энергия, выданная во внешнюю нагрузку (V2L/V2V).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_DISCHARGING_ESTIMATED_TIME",
        type = 2,
        key = "CHARGE_FUNC_DISCHARGING_ESTIMATED_TIME",
        value = 605356800,
        description = "Оценка времени до завершения раздачи энергии.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_DISCHARGING_SOC",
        type = 2,
        key = "CHARGE_FUNC_DISCHARGING_SOC",
        value = 605160192,
        description = "Текущий SoC при V2L/V2V.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_DISCHARGING_SOC_MAX",
        type = 2,
        key = "CHARGE_FUNC_DISCHARGING_SOC_MAX",
        value = 605160448,
        description = "Порог макс. SoC для V2L/V2V.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_DISCHARGING_SOC_MIN",
        type = 2,
        key = "CHARGE_FUNC_DISCHARGING_SOC_MIN",
        value = 605160704,
        description = "Порог мин. SoC для V2L/V2V.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_DISCHARGING_SOC_STEP",
        type = 2,
        key = "CHARGE_FUNC_DISCHARGING_SOC_STEP",
        value = 605160960,
        description = "Шаг изменения SoC для V2L/V2V.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_DISCHARGING_SWITCH_V2L",
        type = 2,
        key = "CHARGE_FUNC_DISCHARGING_SWITCH_V2L",
        value = 605159936,
        description = "Включение режима V2L (питание внешних устройств).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_DISCHARGING_SWITCH_V2V",
        type = 2,
        key = "CHARGE_FUNC_DISCHARGING_SWITCH_V2V",
        value = 605159680,
        description = "Включение режима V2V (подзарядка другого авто).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_DISCHARGING_WORK_CURRENT",
        type = 2,
        key = "CHARGE_FUNC_DISCHARGING_WORK_CURRENT",
        value = 605356544,
        description = "Ток при V2L/V2V.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_DISCHARGING_WORK_VOLTAGE",
        type = 2,
        key = "CHARGE_FUNC_DISCHARGING_WORK_VOLTAGE",
        value = 605356288,
        description = "Напряжение при V2L/V2V.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_DISTANCE_INTERVAL_MAINTAIN",
        type = 2,
        key = "CHARGE_FUNC_DISTANCE_INTERVAL_MAINTAIN",
        value = 609225216,
        description = "Поддержание дистанции ради экономии энергии.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_DISTANCE_PROTECTION",
        type = 2,
        key = "CHARGE_FUNC_DISTANCE_PROTECTION",
        value = 609222912,
        description = "Ограничение расхода для сохранения запаса хода.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_DISTANCE_PROTECTION_UNIT",
        type = 2,
        key = "CHARGE_FUNC_DISTANCE_PROTECTION_UNIT",
        value = 609223168,
        description = "Единицы для параметров защиты запаса хода.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_ENDURANCE_MILEAGE",
        type = 2,
        key = "CHARGE_FUNC_ENDURANCE_MILEAGE",
        value = 606079744,
        description = "Запас хода (оценка).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_EXTERNAL_CHARGING_LIGHT",
        type = 2,
        key = "CHARGE_FUNC_EXTERNAL_CHARGING_LIGHT",
        value = 605031936,
        description = "Внешний индикатор состояния зарядки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_EXTERNAL_POWER_SUPPLY",
        type = 2,
        key = "CHARGE_FUNC_EXTERNAL_POWER_SUPPLY",
        value = 606078976,
        description = "Питание внешних устройств от авто (V2L).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_FUEL_TO_BATT_NOTWORK_TOAST",
        type = 2,
        key = "CHARGE_FUNC_FUEL_TO_BATT_NOTWORK_TOAST",
        value = 609225984,
        description = "Уведомление: заряд от ДВС недоступен.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_GEAR_LVL_INDCN",
        type = 2,
        key = "CHARGE_FUNC_GEAR_LVL_INDCN",
        value = 609225728,
        description = "Индикация уровня передачи для энергорежимов.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_HV_BATT_ACCHRGNP",
        type = 2,
        key = "CHARGE_FUNC_HV_BATT_ACCHRGNP",
        value = 609223936,
        description = "Состояние AC‑разъёма HV‑батареи.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_HV_BATT_CHRG",
        type = 2,
        key = "CHARGE_FUNC_HV_BATT_CHRG",
        value = 609223424,
        description = "Состояние зарядки HV‑батареи.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_HV_BATT_CHRG_TIME",
        type = 2,
        key = "CHARGE_FUNC_HV_BATT_CHRG_TIME",
        value = 609224960,
        description = "Время до завершения зарядки HV‑батареи.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_HV_BATT_DCCHRGNP",
        type = 2,
        key = "CHARGE_FUNC_HV_BATT_DCCHRGNP",
        value = 609224192,
        description = "Состояние DC‑разъёма HV‑батареи.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_HV_BATT_DCHRGNP",
        type = 2,
        key = "CHARGE_FUNC_HV_BATT_DCHRGNP",
        value = 609224448,
        description = "Состояние разъёма раздачи HV‑батареи.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_HV_DIS_CHRG_STS",
        type = 2,
        key = "CHARGE_FUNC_HV_DIS_CHRG_STS",
        value = 609223680,
        description = "Состояние режима раздачи энергии (discharge).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_MAINTAIN_BATTERY_TEMP",
        type = 2,
        key = "CHARGE_FUNC_MAINTAIN_BATTERY_TEMP",
        value = 605030912,
        description = "Поддержание температуры HV‑батареи при зарядке.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_NOTIFICATION_WRONG_OPERATION_REMIND",
        type = 2,
        key = "CHARGE_FUNC_NOTIFICATION_WRONG_OPERATION_REMIND",
        value = 606142720,
        description = "Напоминание о некорректной операции зарядки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_PHEV_PARKING_POWER",
        type = 2,
        key = "CHARGE_FUNC_PHEV_PARKING_POWER",
        value = 605358080,
        description = "Питание при парковке для PHEV.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_PRE_CHARGING",
        type = 2,
        key = "CHARGE_FUNC_PRE_CHARGING",
        value = 605094144,
        description = "Настройки отложенной/плановой зарядки.",
        possibleValues = mapOf(
            "PRE_CHARGING_STATUS_CANCELED" to 605094918,
            "PRE_CHARGING_STATUS_CANCEL_FAILED" to 605094919,
            "PRE_CHARGING_STATUS_CHARGING" to 605094917,
            "PRE_CHARGING_STATUS_FAILED" to 605094914,
            "PRE_CHARGING_STATUS_FAILURE" to 605094915,
            "PRE_CHARGING_STATUS_SCHEDULING" to 605094916,
            "PRE_CHARGING_STATUS_SUCCEED" to 605094913,
            "PRE_CHARGING_STATUS_TIMEOUT" to 605094920,
            "PRE_CHARGING_STATUS_UNKNOWN" to 255,
        )
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_PRE_CHARGING_IMMEDIATELY",
        type = 2,
        key = "CHARGE_FUNC_PRE_CHARGING_IMMEDIATELY",
        value = 605095424,
        description = "Немедленный старт запланированной зарядки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_PRE_CHARGING_STATUS",
        type = 2,
        key = "CHARGE_FUNC_PRE_CHARGING_STATUS",
        value = 605094912,
        description = "Статус отложенной/плановой зарядки.",
        possibleValues = mapOf(
            "PRE_CHARGING_STATUS_CANCELED" to 605094918,
            "PRE_CHARGING_STATUS_CANCEL_FAILED" to 605094919,
            "PRE_CHARGING_STATUS_CHARGING" to 605094917,
            "PRE_CHARGING_STATUS_FAILED" to 605094914,
            "PRE_CHARGING_STATUS_FAILURE" to 605094915,
            "PRE_CHARGING_STATUS_SCHEDULING" to 605094916,
            "PRE_CHARGING_STATUS_SUCCEED" to 605094913,
            "PRE_CHARGING_STATUS_TIMEOUT" to 605094920,
            "PRE_CHARGING_STATUS_UNKNOWN" to 255,
        )
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_PRE_CHARGING_TYPE",
        type = 2,
        key = "CHARGE_FUNC_PRE_CHARGING_TYPE",
        value = 605095168,
        description = "Тип расписания зарядки (выкл/разово/циклично).",
        possibleValues = mapOf(
            "CHARGE_FUNC_PRE_CHARGING_TYPE_CYCLE" to 605095170,
            "CHARGE_FUNC_PRE_CHARGING_TYPE_OFF" to 605095168,
            "CHARGE_FUNC_PRE_CHARGING_TYPE_SINGLE" to 605095169,
        )
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_TIME_INTERVAL_MAINTAIN",
        type = 2,
        key = "CHARGE_FUNC_TIME_INTERVAL_MAINTAIN",
        value = 609225472,
        description = "Управление по времени (энергоуправление).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_TRAVEL_HVAC",
        type = 2,
        key = "CHARGE_FUNC_TRAVEL_HVAC",
        value = 606078208,
        description = "Параметры климата в поездке (энергопотребление).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_WARM_UP",
        type = 2,
        key = "CHARGE_FUNC_WARM_UP",
        value = 605030944,
        description = "Режим прогрева HV‑системы/протокола зарядки.",
        possibleValues = mapOf(
            "WARM_UP_ECO" to 605030929,
            "WARM_UP_SPORT" to 605030930,
        )
    ),
    PropertyData(
        alias = "ICharging.CHARGE_FUNC_WARM_UP_LEVEL",
        type = 2,
        key = "CHARGE_FUNC_WARM_UP_LEVEL",
        value = 605030928,
        description = "Уровень прогрева/подготовки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DM_FUNC_DM_CUSTOM_BPF",
        type = 2,
        key = "DM_FUNC_DM_CUSTOM_BPF",
        value = 570622976,
        description = "Профиль Custom: BPF.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DM_FUNC_DM_CUSTOM_CLIMATE_MODE",
        type = 2,
        key = "DM_FUNC_DM_CUSTOM_CLIMATE_MODE",
        value = 570624512,
        description = "Профиль Custom: климат‑система.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DM_FUNC_DM_CUSTOM_DRIVER_INFO",
        type = 2,
        key = "DM_FUNC_DM_CUSTOM_DRIVER_INFO",
        value = 570625024,
        description = "Профиль Custom: оформление/информация на панели.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DM_FUNC_DM_CUSTOM_EN_START_STOP",
        type = 2,
        key = "DM_FUNC_DM_CUSTOM_EN_START_STOP",
        value = 570625536,
        description = "Профиль Custom: система Start/Stop.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DM_FUNC_DM_CUSTOM_INFOR_THEME",
        type = 2,
        key = "DM_FUNC_DM_CUSTOM_INFOR_THEME",
        value = 570624768,
        description = "Профиль Custom: тема панели.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DM_FUNC_DM_CUSTOM_INTERIOR_LIGHT",
        type = 2,
        key = "DM_FUNC_DM_CUSTOM_INTERIOR_LIGHT",
        value = 570625280,
        description = "Профиль Custom: подсветка салона.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DM_FUNC_DM_CUSTOM_PROPULSION_SYS",
        type = 2,
        key = "DM_FUNC_DM_CUSTOM_PROPULSION_SYS",
        value = 570622208,
        description = "Профиль Custom: силовая установка.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DM_FUNC_DM_CUSTOM_RAB",
        type = 2,
        key = "DM_FUNC_DM_CUSTOM_RAB",
        value = 570622720,
        description = "Профиль Custom: RAB.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DM_FUNC_DM_CUSTOM_STEERING_WHEEL_FEEL",
        type = 2,
        key = "DM_FUNC_DM_CUSTOM_STEERING_WHEEL_FEEL",
        value = 570624256,
        description = "Профиль Custom: усилие на руле.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DM_FUNC_DM_CUSTOM_SUSPENSION_MODE",
        type = 2,
        key = "DM_FUNC_DM_CUSTOM_SUSPENSION_MODE",
        value = 570622464,
        description = "Профиль Custom: настройки подвески.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DM_FUNC_DRIVE_MODE_SELECT",
        type = 2,
        key = "DM_FUNC_DRIVE_MODE_SELECT",
        value = 570491136,
        description = "Переключение режимов вождения.",
        possibleValues = mapOf(
            "DRIVE_MODE_SELECTION_ADAPTIVE" to 570491158,
            "DRIVE_MODE_SELECTION_AWD" to 570491150,
            "DRIVE_MODE_SELECTION_COMFORT" to 570491138,
            "DRIVE_MODE_SELECTION_CUSTOM" to 570491200,
            "DRIVE_MODE_SELECTION_DYNAMIC" to 570491139,
            "DRIVE_MODE_SELECTION_EAWD" to 570491154,
            "DRIVE_MODE_SELECTION_ECO" to 570491137,
            "DRIVE_MODE_SELECTION_ECO_HEV_PHEV" to 570491152,
            "DRIVE_MODE_SELECTION_HDC" to 570491141,
            "DRIVE_MODE_SELECTION_HYBRID" to 570491143,
            "DRIVE_MODE_SELECTION_MUD" to 570491146,
            "DRIVE_MODE_SELECTION_NORMAL" to 570491153,
            "DRIVE_MODE_SELECTION_OFFROAD" to 570491155,
            "DRIVE_MODE_SELECTION_PHEV" to 570491148,
            "DRIVE_MODE_SELECTION_POWER" to 570491144,
            "DRIVE_MODE_SELECTION_PURE" to 570491142,
            "DRIVE_MODE_SELECTION_ROCK" to 570491147,
            "DRIVE_MODE_SELECTION_SAND" to 570491149,
            "DRIVE_MODE_SELECTION_SAVE" to 570491151,
            "DRIVE_MODE_SELECTION_SNOW" to 570491145,
            "DRIVE_MODE_SELECTION_START_TYPE18" to 570491159,
            "DRIVE_MODE_SELECTION_START_TYPE72" to 570491160,
            "DRIVE_MODE_SELECTION_START_TYPE79" to 570491161,
            "DRIVE_MODE_SELECTION_START_TYPE97" to 570491162,
            "DRIVE_MODE_SELECTION_UNKNOWN" to 255,
            "DRIVE_MODE_SELECTION_XC" to 570491140,
            "DRIVE_MODE_SPORT_PLUS" to 570491157,
        )
    ),
    PropertyData(
        alias = "IDriveMode.DM_FUNC_STEERING_WHEEL_FEEL_SYNC_DRIVEMODE",
        type = 2,
        key = "DM_FUNC_STEERING_WHEEL_FEEL_SYNC_DRIVEMODE",
        value = 570688256,
        description = "Связать усилие на руле с режимом вождения.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_ECO_PLUS",
        type = 2,
        key = "DRIVE_MODE_ECO_PLUS",
        value = 570491156,
        description = "Режим вождения: Эко+.",
        emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_ADAPTIVE",
        type = 2,
        key = "DRIVE_MODE_SELECTION_ADAPTIVE",
        value = 570491158,
        description = "Режим вождения: Адаптивный режим.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_AWD",
        type = 2,
        key = "DRIVE_MODE_SELECTION_AWD",
        value = 570491150,
        description = "Режим вождения: Полный привод.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_COMFORT",
        type = 2,
        key = "DRIVE_MODE_SELECTION_COMFORT",
        value = 570491138,
        description = "Режим вождения: Комфортный режим.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_CUSTOM",
        type = 2,
        key = "DRIVE_MODE_SELECTION_CUSTOM",
        value = 570491200,
        description = "Режим вождения: Пользовательский профиль.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_DYNAMIC",
        type = 2,
        key = "DRIVE_MODE_SELECTION_DYNAMIC",
        value = 570491139,
        description = "Режим вождения: Динамичный режим.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_ECO",
        type = 2,
        key = "DRIVE_MODE_SELECTION_ECO",
        value = 570491137,
        description = "Режим вождения: Экономичный режим.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_ECO_HEV_PHEV",
        type = 2,
        key = "DRIVE_MODE_SELECTION_ECO_HEV_PHEV",
        value = 570491152,
        description = "Режим вождения: Эко (HEV/PHEV).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_HDC",
        type = 2,
        key = "DRIVE_MODE_SELECTION_HDC",
        value = 570491141,
        description = "Режим вождения: Помощь при спуске (HDC).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_HYBRID",
        type = 2,
        key = "DRIVE_MODE_SELECTION_HYBRID",
        value = 570491143,
        description = "Режим вождения: Гибридный режим.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_MUD",
        type = 2,
        key = "DRIVE_MODE_SELECTION_MUD",
        value = 570491146,
        description = "Режим вождения: Для грязи.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_NORMAL",
        type = 2,
        key = "DRIVE_MODE_SELECTION_NORMAL",
        value = 570491153,
        description = "Режим вождения: Стандартный режим.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_OFFROAD",
        type = 2,
        key = "DRIVE_MODE_SELECTION_OFFROAD",
        value = 570491155,
        description = "Режим вождения: Внедорожный режим.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_PHEV",
        type = 2,
        key = "DRIVE_MODE_SELECTION_PHEV",
        value = 570491148,
        description = "Режим вождения: Режим PHEV.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_POWER",
        type = 2,
        key = "DRIVE_MODE_SELECTION_POWER",
        value = 570491144,
        description = "Режим вождения: Максимальная отдача.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_PURE",
        type = 2,
        key = "DRIVE_MODE_SELECTION_PURE",
        value = 570491142,
        description = "Режим вождения: Чисто электрический ход.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_ROCK",
        type = 2,
        key = "DRIVE_MODE_SELECTION_ROCK",
        value = 570491147,
        description = "Режим вождения: Для каменистых участков.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_SAND",
        type = 2,
        key = "DRIVE_MODE_SELECTION_SAND",
        value = 570491149,
        description = "Режим вождения: Для песка.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_SAVE",
        type = 2,
        key = "DRIVE_MODE_SELECTION_SAVE",
        value = 570491151,
        description = "Режим вождения: Сохранение заряда.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_SNOW",
        type = 2,
        key = "DRIVE_MODE_SELECTION_SNOW",
        value = 570491145,
        description = "Режим вождения: Для снега.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_START_TYPE18",
        type = 2,
        key = "DRIVE_MODE_SELECTION_START_TYPE18",
        value = 570491159,
        description = "Режим вождения: Служебный профиль запуска (тип 18).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_START_TYPE18",
        type = 2,
        key = "DRIVE_MODE_SELECTION_START_TYPE72",
        value = 570491160,
        description = "Режим вождения: Служебный профиль запуска (тип 72).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_START_TYPE79",
        type = 2,
        key = "DRIVE_MODE_SELECTION_START_TYPE79",
        value = 570491161,
        description = "Режим вождения: Служебный профиль запуска (тип 79).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_START_TYPE97",
        type = 2,
        key = "DRIVE_MODE_SELECTION_START_TYPE97",
        value = 570491162,
        description = "Режим вождения: Служебный профиль запуска (тип 97).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_XC",
        type = 2,
        key = "DRIVE_MODE_SELECTION_XC",
        value = 570491140,
        description = "Режим вождения: Кросс‑кантри.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_SELECTION_NORMAL",
        type = 2,
        key = "DRIVE_MODE_SELECTION_eAWD",
        value = 570491154,
        description = "Режим вождения: Электрический полный привод.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DRIVE_MODE_ECO_PLUS",
        type = 2,
        key = "DRIVE_MODE_SPORT_PLUS",
        value = 570491157,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.ENERGY_REGENERATION_LEVEL_AUTO",
        type = 2,
        key = "ENERGY_REGENERATION_LEVEL_AUTO",
        value = 537003268,
        description = "Уровень рекуперации: автоматический.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.ENERGY_REGENERATION_LEVEL_HIGH",
        type = 2,
        key = "ENERGY_REGENERATION_LEVEL_HIGH",
        value = 537003267,
        description = "Уровень рекуперации: высокий.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.ENERGY_REGENERATION_LEVEL_LOW",
        type = 2,
        key = "ENERGY_REGENERATION_LEVEL_LOW",
        value = 537003265,
        description = "Уровень рекуперации: низкий.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.ENERGY_REGENERATION_LEVEL_MID",
        type = 2,
        key = "ENERGY_REGENERATION_LEVEL_MID",
        value = 537003266,
        description = "Уровень рекуперации: средний.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_SPEED_CONTROL_MODE",
        type = 2,
        key = "FUNCTION_SPEED_CONTROL_MODE",
        value = 537069056,
        description = "Режим систем поддержки/ограничения скорости.",
        possibleValues = mapOf(
            "SPEED_CONTROL_MODE_ACC" to 537069058,
            "SPEED_CONTROL_MODE_CC" to 537069057,
            "SPEED_CONTROL_MODE_GPILOT" to 537069059,
            "SPEED_CONTROL_MODE_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IUnits.FUNC_UNIT_AVG_FUEL",
        type = 2,
        key = "FUNC_UNIT_AVG_FUEL",
        value = 620822784,
        description = "Единицы среднего расхода топлива.",
        possibleValues = mapOf(
            "UNIT_AVG_FUEL_KM_L" to 620822786,
            "UNIT_AVG_FUEL_L_100KM" to 620822785,
            "UNIT_AVG_FUEL_UK_MPG" to 620822788,
            "UNIT_AVG_FUEL_US_MPG" to 620822787,
        )
    ),
    PropertyData(
        alias = "IUnits.FUNC_UNIT_DATE_FORMAT",
        type = 2,
        key = "FUNC_UNIT_DATE_FORMAT",
        value = 620888576,
        description = "Формат даты (DMY/MDY/YMD).",
        possibleValues = mapOf(
            "UNIT_DATE_FORMAT_DMY" to 620888578,
            "UNIT_DATE_FORMAT_MDY" to 620888579,
            "UNIT_DATE_FORMAT_YMD" to 620888577,
        )
    ),
    PropertyData(
        alias = "IUnits.FUNC_UNIT_DRIVEN_DISTANCE",
        type = 2,
        key = "FUNC_UNIT_DRIVEN_DISTANCE",
        value = 620823040,
        description = "Единицы расстояния/пробега.",
        possibleValues = mapOf(
            "UNIT_DRIVEN_DISTANCE_KM" to 620823041,
            "UNIT_DRIVEN_DISTANCE_MILES" to 620823042,
        )
    ),
    PropertyData(
        alias = "IUnits.FUNC_UNIT_SPEED",
        type = 2,
        key = "FUNC_UNIT_SPEED",
        value = 620823808,
        description = "Единицы скорости (км/ч, миль/ч).",
        possibleValues = mapOf(
            "SPEED_CONTROL_MODE_ACC" to 537069058,
            "SPEED_CONTROL_MODE_CC" to 537069057,
            "SPEED_CONTROL_MODE_GPILOT" to 537069059,
            "SPEED_CONTROL_MODE_OFF" to 0,
            "SPEED_LIMITATION_MODE_ASL" to 537068802,
            "SPEED_LIMITATION_MODE_AVSL" to 537068801,
            "SPEED_LIMITATION_MODE_OFF" to 0,
            "SPEED_LIMIT_WARNING_MODE_FLASHING" to 671482370,
            "SPEED_LIMIT_WARNING_MODE_NO_WARNING" to 671482369,
            "SPEED_LIMIT_WARNING_MODE_OFF" to 0,
            "SPEED_LIMIT_WARNING_MODE_SOUND" to 671482371,
            "SPEED_LIMIT_WARNING_OFFSET_0KM" to 671482881,
            "SPEED_LIMIT_WARNING_OFFSET_10KM" to 671482883,
            "SPEED_LIMIT_WARNING_OFFSET_5KM" to 671482882,
            "SPEED_LIMIT_WARNING_OFFSET_MINUS_10KM" to 671482885,
            "SPEED_LIMIT_WARNING_OFFSET_MINUS_5KM" to 671482884,
            "SPEED_LIMIT_WARNING_OFFSET_OFF" to 0,
            "UNIT_SPEED_KM_H" to 620823809,
            "UNIT_SPEED_MPH" to 620823810,
        )
    ),
    PropertyData(
        alias = "IUnits.FUNC_UNIT_TEMPERATURE",
        type = 2,
        key = "FUNC_UNIT_TEMPERATURE",
        value = 620823296,
        description = "Единицы температуры (°C/°F).",
        possibleValues = mapOf(
            "TEMPERATURE_UNIT_C" to 268830209,
            "TEMPERATURE_UNIT_F" to 268830210,
            "UNIT_TEMPERATURE_C" to 620823297,
            "UNIT_TEMPERATURE_F" to 620823298,
        )
    ),
    PropertyData(
        alias = "IUnits.FUNC_UNIT_TIME_INDICATION",
        type = 2,
        key = "FUNC_UNIT_TIME_INDICATION",
        value = 620888320,
        description = "Формат времени (24ч/AM‑PM).",
        possibleValues = mapOf(
            "UNIT_TIME_INDICATION_24H" to 620888322,
            "UNIT_TIME_INDICATION_AM_PM" to 620888321,
        )
    ),
    PropertyData(
        alias = "IUnits.FUNC_UNIT_TIRE_PRESSURE",
        type = 2,
        key = "FUNC_UNIT_TIRE_PRESSURE",
        value = 620823552,
        description = "Единицы давления в шинах (kPa/bar/psi).",
        possibleValues = mapOf(
            "UNIT_TIRE_PRESSURE_BAR" to 620823554,
            "UNIT_TIRE_PRESSURE_KPA" to 620823553,
            "UNIT_TIRE_PRESSURE_PSI" to 620823555,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AC",
        type = 2,
        key = "HVAC_FUNC_AC",
        value = 268501760,
        description = "Включение компрессора кондиционера (A/C).",
        possibleValues = mapOf(
            "AC_ON" to 1,
            "AC_OFF" to 0
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AC_MAX",
        type = 2,
        key = "HVAC_FUNC_AC_MAX",
        value = 268502016,
        description = "MAX A/C: максимальное охлаждение.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AIR_FRAGRANCE",
        type = 2,
        key = "HVAC_FUNC_AIR_FRAGRANCE",
        value = 269156608,
        description = "Система ароматизации салона (тип/уровень/слот).",
        possibleValues = mapOf(
            "AIR_FRAGRANCE_JASMINE" to 269156870,
            "AIR_FRAGRANCE_LAVENDER" to 269156867,
            "AIR_FRAGRANCE_LEVEL_1" to 269157121,
            "AIR_FRAGRANCE_LEVEL_2" to 269157122,
            "AIR_FRAGRANCE_LEVEL_3" to 269157123,
            "AIR_FRAGRANCE_LEVEL_OFF" to 0,
            "AIR_FRAGRANCE_LILY" to 269156866,
            "AIR_FRAGRANCE_LONGJING" to 269156868,
            "AIR_FRAGRANCE_OFF" to 0,
            "AIR_FRAGRANCE_ROSE" to 269156865,
            "AIR_FRAGRANCE_SANDALWOOD" to 269156869,
            "AIR_FRAGRANCE_SLOT_1" to 269157377,
            "AIR_FRAGRANCE_SLOT_2" to 269157378,
            "AIR_FRAGRANCE_SLOT_3" to 269157379,
            "AIR_FRAGRANCE_SLOT_4" to 269157380,
            "AIR_FRAGRANCE_SLOT_5" to 269157381,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AIR_FRAGRANCE_LEVEL",
        type = 2,
        key = "HVAC_FUNC_AIR_FRAGRANCE_LEVEL",
        value = 269157120,
        description = "Интенсивность ароматизации.",
        possibleValues = mapOf(
            "AIR_FRAGRANCE_LEVEL_1" to 269157121,
            "AIR_FRAGRANCE_LEVEL_2" to 269157122,
            "AIR_FRAGRANCE_LEVEL_3" to 269157123,
            "AIR_FRAGRANCE_LEVEL_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IFragrance.HVAC_FUNC_AIR_FRAGRANCE_LOW",
        type = 2,
        key = "HVAC_FUNC_AIR_FRAGRANCE_LOW",
        value = 269157888,
        description = "Низкая интенсивность ароматизации.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IFragrance.HVAC_FUNC_AIR_FRAGRANCE_SLOT",
        type = 2,
        key = "HVAC_FUNC_AIR_FRAGRANCE_SLOT",
        value = 269157376,
        description = "Выбор картриджа аромата (слот).",
        possibleValues = mapOf(
            "AIR_FRAGRANCE_SLOT_1" to 269157377,
            "AIR_FRAGRANCE_SLOT_2" to 269157378,
            "AIR_FRAGRANCE_SLOT_3" to 269157379,
            "AIR_FRAGRANCE_SLOT_4" to 269157380,
            "AIR_FRAGRANCE_SLOT_5" to 269157381,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AIR_FRAGRANCE_TYPE",
        type = 2,
        key = "HVAC_FUNC_AIR_FRAGRANCE_TYPE",
        value = 269156864,
        description = "Тип выбранного аромата (код).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IFragrance.HVAC_FUNC_AIR_FRAGRANCE_TYPE_ID",
        type = 2,
        key = "HVAC_FUNC_AIR_FRAGRANCE_TYPE_ID",
        value = 269157632,
        description = "Идентификатор аромата (код).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AI_POWER",
        type = 2,
        key = "HVAC_FUNC_AI_POWER",
        value = 269091840,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AQS_STATUS",
        type = 2,
        key = "HVAC_FUNC_AQS_STATUS",
        value = 269751808,
        description = "Состояние системы контроля качества воздуха (AQS).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO",
        type = 2,
        key = "HVAC_FUNC_AUTO",
        value = 268501504,
        description = "Режим автоматического управления климатом.",
        possibleValues = mapOf(
            "AUTO_CLOSE_WINDOW_KEY_LONG_PRESS" to 537396226,
            "AUTO_CLOSE_WINDOW_OFF" to 0,
            "AUTO_CLOSE_WINDOW_VEHICLE_LOCK" to 537396225,
            "AUTO_FAN_SETTING_HIGH" to 268567043,
            "AUTO_FAN_SETTING_HIGHER" to 268567045,
            "AUTO_FAN_SETTING_NORMAL" to 268567042,
            "AUTO_FAN_SETTING_QUIETER" to 268567044,
            "AUTO_FAN_SETTING_SILENT" to 268567041,
            "AUTO_RESET_OPTION_4_HOURS" to 612369153,
            "AUTO_RESET_OPTION_CHARGING" to 612369154,
            "AUTO_RESET_OPTION_PARKING" to 612369156,
            "AUTO_RESET_OPTION_PARKING_OIL" to 612369155,
            "AUTO_SEAT_HEATING_LEVEL_1" to 268764417,
            "AUTO_SEAT_HEATING_LEVEL_2" to 268764418,
            "AUTO_SEAT_HEATING_LEVEL_3" to 268764419,
            "AUTO_SEAT_HEATING_OFF" to 0,
            "AUTO_SEAT_HEATING_TIME_1" to 268764673,
            "AUTO_SEAT_HEATING_TIME_2" to 268764674,
            "AUTO_SEAT_HEATING_TIME_3" to 268764675,
            "AUTO_SEAT_HEATING_TIME_4" to 268764676,
            "AUTO_SEAT_HEATING_TIME_OFF" to 0,
            "AUTO_SEAT_MASSAGE_LEVEL_1" to 268765185,
            "AUTO_SEAT_MASSAGE_LEVEL_2" to 268765186,
            "AUTO_SEAT_MASSAGE_LEVEL_3" to 268765187,
            "AUTO_SEAT_MASSAGE_OFF" to 0,
            "AUTO_SEAT_MASSAGE_TIME_1" to 268765441,
            "AUTO_SEAT_MASSAGE_TIME_2" to 268765442,
            "AUTO_SEAT_MASSAGE_TIME_3" to 268765443,
            "AUTO_SEAT_MASSAGE_TIME_OFF" to 0,
            "AUTO_SEAT_VENTILATION_TIME_1" to 268764161,
            "AUTO_SEAT_VENTILATION_TIME_2" to 268764162,
            "AUTO_SEAT_VENTILATION_TIME_3" to 268764163,
            "AUTO_SEAT_VENTILATION_TIME_4" to 268764164,
            "AUTO_SEAT_VENTILATION_TIME_OFF" to 0,
            "AUTO_STEERING_WHEEL_HEAT_HIGH" to 269025795,
            "AUTO_STEERING_WHEEL_HEAT_LOW" to 269025793,
            "AUTO_STEERING_WHEEL_HEAT_MID" to 269025794,
            "AUTO_STEERING_WHEEL_HEAT_TIME_1" to 269026049,
            "AUTO_STEERING_WHEEL_HEAT_TIME_2" to 269026050,
            "AUTO_STEERING_WHEEL_HEAT_TIME_3" to 269026051,
            "AUTO_STEERING_WHEEL_HEAT_TIME_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTOMATIC_VENTILATION_DRY",
        type = 2,
        key = "HVAC_FUNC_AUTOMATIC_VENTILATION_DRY",
        value = 269485312,
        description = "Автосушка испарителя после выключения A/C.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_CLOSE_WINDOW_REMIND",
        type = 2,
        key = "HVAC_FUNC_AUTO_CLOSE_WINDOW_REMIND",
        value = 269418752,
        description = "Напоминание закрыть окна для эффективности климата.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_CLOSE_WINDOW_REMIND_REQUEST",
        type = 2,
        key = "HVAC_FUNC_AUTO_CLOSE_WINDOW_REMIND_REQUEST",
        value = 269419008,
        description = "Запрос напоминания о закрытии окон.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_CONTROL",
        type = 2,
        key = "HVAC_FUNC_AUTO_CONTROL",
        value = 269749248,
        description = "Автоматическое управление климат‑системой.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_CZIS",
        type = 2,
        key = "HVAC_FUNC_AUTO_CZIS",
        value = 269485568,
        description = "Авто‑режим системы очистки воздуха CZIS.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_DEFROST_CONFIRM",
        type = 2,
        key = "HVAC_FUNC_AUTO_DEFROST_CONFIRM",
        value = 268699392,
        description = "Подтверждение автозапуска обогрева стекла.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_DEFROST_FRONT",
        type = 2,
        key = "HVAC_FUNC_AUTO_DEFROST_FRONT",
        value = 268698880,
        description = "Автовключение обдува лобового стекла.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_DEFROST_REAR",
        type = 2,
        key = "HVAC_FUNC_AUTO_DEFROST_REAR",
        value = 268698624,
        description = "Автовключение обогрева заднего стекла.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_DEFROST_REQUEST",
        type = 2,
        key = "HVAC_FUNC_AUTO_DEFROST_REQUEST",
        value = 268699136,
        description = "Запрос на автоматический дефрост.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_ELECTRIC_DEFROST",
        type = 2,
        key = "HVAC_FUNC_ELECTRIC_DEFROST",
        value = 269027328,
        description = "Электрообогрев зоны лобового стекла.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_FAN_SETTING",
        type = 2,
        key = "HVAC_FUNC_AUTO_FAN_SETTING",
        value = 268567040,
        description = "Профиль авто‑скорости вентилятора.",
        possibleValues = mapOf(
            "AUTO_FAN_SETTING_HIGH" to 268567043,
            "AUTO_FAN_SETTING_HIGHER" to 268567045,
            "AUTO_FAN_SETTING_NORMAL" to 268567042,
            "AUTO_FAN_SETTING_QUIETER" to 268567044,
            "AUTO_FAN_SETTING_SILENT" to 268567041,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_FAN_SPEED_HARD_KEY",
        type = 2,
        key = "HVAC_FUNC_AUTO_FAN_SPEED_HARD_KEY",
        value = 268567552,
        description = "Событие клавиши авто‑скорости вентилятора.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_ION",
        type = 2,
        key = "HVAC_FUNC_AUTO_ION",
        value = 269222144,
        description = "Автовключение ионизатора.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_ION_CONFIRM",
        type = 2,
        key = "HVAC_FUNC_AUTO_ION_CONFIRM",
        value = 269222656,
        description = "Подтверждение авто‑ионизатора.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_ION_REQUEST",
        type = 2,
        key = "HVAC_FUNC_AUTO_ION_REQUEST",
        value = 269222400,
        description = "Запрос на авто‑ионизатор.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IFragrance.HVAC_FUNC_AUTO_REFRESHING_FRAGRANCE",
        type = 2,
        key = "HVAC_FUNC_AUTO_REFRESHING_FRAGRANCE",
        value = 269160704,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_SEAT_HEATING",
        type = 2,
        key = "HVAC_FUNC_AUTO_SEAT_HEATING",
        value = 268764416,
        possibleValues = mapOf(
            "AUTO_SEAT_HEATING_LEVEL_1" to 268764417,
            "AUTO_SEAT_HEATING_LEVEL_2" to 268764418,
            "AUTO_SEAT_HEATING_LEVEL_3" to 268764419,
            "AUTO_SEAT_HEATING_OFF" to 0,
            "AUTO_SEAT_HEATING_TIME_1" to 268764673,
            "AUTO_SEAT_HEATING_TIME_2" to 268764674,
            "AUTO_SEAT_HEATING_TIME_3" to 268764675,
            "AUTO_SEAT_HEATING_TIME_4" to 268764676,
            "AUTO_SEAT_HEATING_TIME_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_SEAT_HEATING_TIME",
        type = 2,
        key = "HVAC_FUNC_AUTO_SEAT_HEATING_TIME",
        value = 268764672,
        possibleValues = mapOf(
            "AUTO_SEAT_HEATING_TIME_1" to 268764673,
            "AUTO_SEAT_HEATING_TIME_2" to 268764674,
            "AUTO_SEAT_HEATING_TIME_3" to 268764675,
            "AUTO_SEAT_HEATING_TIME_4" to 268764676,
            "AUTO_SEAT_HEATING_TIME_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_SEAT_MASSAGE_TIME",
        type = 2,
        key = "HVAC_FUNC_AUTO_SEAT_MASSAGE_TIME",
        value = 268765440,
        possibleValues = mapOf(
            "AUTO_SEAT_MASSAGE_TIME_1" to 268765441,
            "AUTO_SEAT_MASSAGE_TIME_2" to 268765442,
            "AUTO_SEAT_MASSAGE_TIME_3" to 268765443,
            "AUTO_SEAT_MASSAGE_TIME_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_SEAT_VENTILATION_TIME",
        type = 2,
        key = "HVAC_FUNC_AUTO_SEAT_VENTILATION_TIME",
        value = 268764160,
        possibleValues = mapOf(
            "AUTO_SEAT_VENTILATION_TIME_1" to 268764161,
            "AUTO_SEAT_VENTILATION_TIME_2" to 268764162,
            "AUTO_SEAT_VENTILATION_TIME_3" to 268764163,
            "AUTO_SEAT_VENTILATION_TIME_4" to 268764164,
            "AUTO_SEAT_VENTILATION_TIME_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_STEERING_WHEEL_HEAT",
        type = 2,
        key = "HVAC_FUNC_AUTO_STEERING_WHEEL_HEAT",
        value = 269025792,
        possibleValues = mapOf(
            "AUTO_STEERING_WHEEL_HEAT_HIGH" to 269025795,
            "AUTO_STEERING_WHEEL_HEAT_LOW" to 269025793,
            "AUTO_STEERING_WHEEL_HEAT_MID" to 269025794,
            "AUTO_STEERING_WHEEL_HEAT_TIME_1" to 269026049,
            "AUTO_STEERING_WHEEL_HEAT_TIME_2" to 269026050,
            "AUTO_STEERING_WHEEL_HEAT_TIME_3" to 269026051,
            "AUTO_STEERING_WHEEL_HEAT_TIME_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_STEERING_WHEEL_HEAT_SWITCH",
        type = 2,
        key = "HVAC_FUNC_AUTO_STEERING_WHEEL_HEAT_SWITCH",
        value = 269026304,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_STEERING_WHEEL_HEAT_TIME",
        type = 2,
        key = "HVAC_FUNC_AUTO_STEERING_WHEEL_HEAT_TIME",
        value = 269026048,
        possibleValues = mapOf(
            "AUTO_STEERING_WHEEL_HEAT_TIME_1" to 269026049,
            "AUTO_STEERING_WHEEL_HEAT_TIME_2" to 269026050,
            "AUTO_STEERING_WHEEL_HEAT_TIME_3" to 269026051,
            "AUTO_STEERING_WHEEL_HEAT_TIME_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_BLOWING_MODE",
        type = 2,
        key = "HVAC_FUNC_BLOWING_MODE",
        value = 268894464,
        description = "Распределение потоков воздуха (лицо/ноги/стекло/авто).",
        possibleValues = mapOf(
            "BLOWING_MODE_AUTO_SWITCH" to 268894472,
            "BLOWING_MODE_FACE" to 268894465,
            "BLOWING_MODE_FACE_AND_FRONT_WINDOW" to 268894469,
            "BLOWING_MODE_FACE_AND_LEG" to 268894467,
            "BLOWING_MODE_FRONT_WINDOW" to 268894468,
            "BLOWING_MODE_LEG" to 268894466,
            "BLOWING_MODE_LEG_AND_FRONT_WINDOW" to 268894470,
            "BLOWING_MODE_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_BLOWING_MODE_HARD_KEY",
        type = 2,
        key = "HVAC_FUNC_BLOWING_MODE_HARD_KEY",
        value = 268896256,
        description = "Событие клавиши выбора направления обдува.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_CIRCULATION",
        type = 2,
        key = "HVAC_FUNC_CIRCULATION",
        value = 268632320,
        description = "Режим рециркуляции: внутренняя/наружная/авто.",
        possibleValues = mapOf(
            "CIRCULATION_AUTO" to 268632323,
            "CIRCULATION_INNER" to 268632321,
            "CIRCULATION_OFF" to 0,
            "CIRCULATION_OUTSIDE" to 268632322,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_CIRCULATION_LONG_TOUCH",
        type = 2,
        key = "HVAC_FUNC_CIRCULATION_LONG_TOUCH",
        value = 268632832,
        description = "Долгое нажатие на кнопку рециркуляции.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_CIRCULATION_TIMER",
        type = 2,
        key = "HVAC_FUNC_CIRCULATION_TIMER",
        value = 268632576,
        description = "Таймер переключения рециркуляции.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_CLIMATE_HARDKEY_SOUND",
        type = 2,
        key = "HVAC_FUNC_CLIMATE_HARDKEY_SOUND",
        value = 269486080,
        description = "Звуковая индикация клавиш климата.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_CLIMATE_LOCK",
        type = 2,
        key = "HVAC_FUNC_CLIMATE_LOCK",
        value = 269484544,
        description = "Блокировка управления климатом.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_CLIMATE_ZONE",
        type = 2,
        key = "HVAC_FUNC_CLIMATE_ZONE",
        value = 268502272,
        description = "Количество климатических зон.",
        possibleValues = mapOf(
            "CLIMATE_ZONE_DUAL" to 268502274,
            "CLIMATE_ZONE_FOUR" to 268502276,
            "CLIMATE_ZONE_SINGLE" to 268502273,
            "CLIMATE_ZONE_TRIPLE" to 268502275,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_CO2_HIGHER_CONFIRM",
        type = 2,
        key = "HVAC_FUNC_CO2_HIGHER_CONFIRM",
        value = 269353728,
        description = "Подтверждение предупреждения о повышенном CO₂.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_DEFROST_FRONT",
        type = 2,
        key = "HVAC_FUNC_DEFROST_FRONT",
        value = 268697856,
        description = "Антизапотевание лобового стекла.",
        possibleValues = mapOf(
            "FRONTDEFROST_ON" to 1,
            "FRONTDEFROST_OFF" to 0
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_DEFROST_FRONT_MAX",
        type = 2,
        key = "HVAC_FUNC_DEFROST_FRONT_MAX",
        value = 268698112,
        description = "Максимальный режим обдува/обогрева лобового стекла.",
        possibleValues = mapOf(
            "FRONTDEFROSTMAX_ON" to 1,
            "FRONTDEFROSTMAX_OFF" to 0
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_DEFROST_REAR",
        type = 2,
        key = "HVAC_FUNC_DEFROST_REAR",
        value = 268698368,
        description = "Обогрев заднего стекла.",
        possibleValues = mapOf(
            "REARDEFROST_ON" to 1,
            "REARDEFROST_OFF" to 0
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_DIRECTION_MODE",
        type = 2,
        key = "HVAC_FUNC_DIRECTION_MODE",
        value = 268894976,
        description = "Режим направленности обдува (Focus/Avoid/Custom).",
        possibleValues = mapOf(
            "DIRECTION_MODE_AVOID" to 268894978,
            "DIRECTION_MODE_CUSTOM" to 268894723,
            "DIRECTION_MODE_FOCUS" to 268894977,
            "DIRECTION_MODE_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_DISPLAY_WINDOW_TAB",
        type = 2,
        key = "HVAC_FUNC_DISPLAY_WINDOW_TAB",
        value = 269484800,
        description = "Текущая вкладка окна климата.",
        possibleValues = mapOf(
            "DISPLAY_WINDOW_TAB_DEFAULT" to 269484801,
            "DISPLAY_WINDOW_TAB_HARDWARE_POP" to 269484804,
            "DISPLAY_WINDOW_TAB_IONS_POP" to 269484806,
            "DISPLAY_WINDOW_TAB_LEFT_TEMP" to 269484802,
            "DISPLAY_WINDOW_TAB_NONE" to 0,
            "DISPLAY_WINDOW_TAB_RIGHT_TEMP" to 269484803,
            "DISPLAY_WINDOW_TAB_SEAT" to 269484805,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_ECO_SWITCH",
        type = 2,
        key = "HVAC_FUNC_ECO_SWITCH",
        value = 268960000,
        description = "Эко‑режим A/C (снижение энергопотребления).",
        possibleValues = mapOf(
            "ACECO_ON" to 1,
            "ACECO_OFF" to 0
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_ELECTRICAL_AIR_VENT",
        type = 2,
        key = "HVAC_FUNC_ELECTRICAL_AIR_VENT",
        value = 269746432,
        description = "Электропривод заслонок воздуховодов.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_FAN_SPEED",
        type = 2,
        key = "HVAC_FUNC_FAN_SPEED",
        value = 268566784,
        description = "Скорость вентилятора климат‑системы.",
        possibleValues = mapOf(
            "FAN_SPEED_LEVEL_1" to 268566785,
            "FAN_SPEED_LEVEL_2" to 268566786,
            "FAN_SPEED_LEVEL_3" to 268566787,
            "FAN_SPEED_LEVEL_4" to 268566788,
            "FAN_SPEED_LEVEL_5" to 268566789,
            "FAN_SPEED_LEVEL_6" to 268566790,
            "FAN_SPEED_LEVEL_7" to 268566791,
            "FAN_SPEED_LEVEL_8" to 268566792,
            "FAN_SPEED_LEVEL_9" to 268566793,
            "FAN_SPEED_LEVEL_AUTO" to 268566794,
            "FAN_SPEED_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_FAN_SPEED_BLOWER",
        type = 2,
        key = "HVAC_FUNC_FAN_SPEED_BLOWER",
        value = 269752064,
        description = "Команда изменить скорость вентилятора.",
        possibleValues = mapOf(
            "HVAC_FUNC_FAN_SPEED_BLOWER_DOWN" to 269752066,
            "HVAC_FUNC_FAN_SPEED_BLOWER_UP" to 269752065,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_FAN_SPEED_HARD_KEY",
        type = 2,
        key = "HVAC_FUNC_FAN_SPEED_HARD_KEY",
        value = 268567296,
        description = "Событие клавиши скорости вентилятора.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_FILTER_ELEMENT_LIFE",
        type = 2,
        key = "HVAC_FUNC_FILTER_ELEMENT_LIFE",
        value = 269746944,
        description = "Оставшийся ресурс салонного фильтра.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_G_CLEAN",
        type = 2,
        key = "HVAC_FUNC_G_CLEAN",
        value = 269485056,
        description = "Интенсивная очистка воздуха (G‑Clean/IAPS).",
        possibleValues = mapOf(
            "GCLEAN_ON" to 1,
            "GCLEAN_OFF" to 0
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_INTELLIGENT_DEODORIZATION",
        type = 2,
        key = "HVAC_FUNC_INTELLIGENT_DEODORIZATION",
        value = 269748224,
        description = "Интеллектуальная дезодорация салона.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_INTELLIGENT_RECOMMENDATION",
        type = 2,
        key = "HVAC_FUNC_INTELLIGENT_RECOMMENDATION",
        value = 269615360,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_IONIZER_CLS_WIN_POPUP",
        type = 2,
        key = "HVAC_FUNC_IONIZER_CLS_WIN_POPUP",
        value = 269751552,
        description = "Поп‑ап «Закройте окна» для ионизатора.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_IONIZER_CLS_WIN_POPUP_SETTING",
        type = 2,
        key = "HVAC_FUNC_IONIZER_CLS_WIN_POPUP_SETTING",
        value = 269751296,
        description = "Настройка поп‑апа «Закройте окна».",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_IONS_SWITCH",
        type = 2,
        key = "HVAC_FUNC_IONS_SWITCH",
        value = 268961024,
        description = "Ионизатор воздуха: вкл/выкл.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_OVERHEAT_PROTECTION",
        type = 2,
        key = "HVAC_FUNC_OVERHEAT_PROTECTION",
        value = 268960768,
        description = "Защита салона от перегрева на стоянке.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_POST_CLIMATISATION",
        type = 2,
        key = "HVAC_FUNC_POST_CLIMATISATION",
        value = 269091328,
        description = "Постклиматизация после поездки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_POWER",
        type = 2,
        key = "HVAC_FUNC_POWER",
        value = 268501248,
        description = "Главный выключатель климат‑системы.",
        possibleValues = mapOf(
            "POWER_CHARGE_MODE_FAIL" to 606078979,
            "POWER_CHARGE_MODE_FINISH" to 606078980,
            "POWER_CHARGE_MODE_FUEL_LOW" to 606078981,
            "POWER_CHARGE_MODE_OFF" to 606078978,
            "POWER_CHARGE_MODE_ON" to 606078977,
            "POWER_CHARGE_MODE_TIMEOUT" to 606078982,
            "POWER_FLOW_BOOST" to 604045570,
            "POWER_FLOW_CHARGE_AC" to 604045582,
            "POWER_FLOW_CHARGE_DC" to 604045583,
            "POWER_FLOW_DISCHARGE" to 604045584,
            "POWER_FLOW_DRIVEN_BY_ELECTRIC_MOTOR_AND_ENGINE" to 604045592,
            "POWER_FLOW_EAWD" to 604045571,
            "POWER_FLOW_ELEC" to 604045574,
            "POWER_FLOW_ENGINEOFF_REGBRAKE" to 604045578,
            "POWER_FLOW_ENGINEONLY" to 604045572,
            "POWER_FLOW_ENGINEONLY_CHARGE" to 604045573,
            "POWER_FLOW_ENGINEON_REGBRAKE" to 604045579,
            "POWER_FLOW_ENGINEON_REGBRAKE_CHARGE" to 604045580,
            "POWER_FLOW_FRONT_ELE_DRIVE" to 604045586,
            "POWER_FLOW_MAIN_CHARGE" to 604045569,
            "POWER_FLOW_NOT_READY" to 0,
            "POWER_FLOW_PURE_ELE_AWD" to 604045585,
            "POWER_FLOW_REAR_ELE_DRIVE" to 604045587,
            "POWER_FLOW_REGENERATION" to 604045589,
            "POWER_FLOW_SAILING" to 604045581,
            "POWER_FLOW_STANDSTILL" to 604045588,
            "POWER_FLOW_STANDSTILL_AND_BOTH_EM_ENGINE_OFF" to 604045590,
            "POWER_FLOW_STANDSTILL_ENGINE_ON_WITH_ISG" to 604045591,
            "POWER_FLOW_STILL_ENGINEOFF" to 604045575,
            "POWER_FLOW_STILL_ENGINEON" to 604045576,
            "POWER_FLOW_STILL_ENGINEON_CHARGE" to 604045577,
            "POWER_TRAIN_STOP_EV_BLOCKED" to 570691585,
            "POWER_TRAIN_STOP_EV_PLUS_BLOCKED" to 570691587,
            "POWER_TRAIN_STOP_HEV_BLOCKED" to 570691586,
            "POWER_TRAIN_STOP_NOT_BLOCKED" to 570691584,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_POWER_VR",
        type = 2,
        key = "HVAC_FUNC_POWER_VR",
        value = 268505344,
        description = "Голосовое включение/выключение климата.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_PRE_CLIMATISATION",
        type = 2,
        key = "HVAC_FUNC_PRE_CLIMATISATION",
        value = 269091072,
        description = "Предклиматизация перед поездкой.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IFragrance.HVAC_FUNC_REFRESHING_FRAGRANCE_POP",
        type = 2,
        key = "HVAC_FUNC_REFRESHING_FRAGRANCE_POP",
        value = 269160960,
        description = "Уведомление об ароматизации.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_RESET_FILTER_ELEMENT_LIFE",
        type = 2,
        key = "HVAC_FUNC_RESET_FILTER_ELEMENT_LIFE",
        value = 269750272,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_SEAT_HEATING",
        type = 2,
        key = "HVAC_FUNC_SEAT_HEATING",
        value = 268763648,
        description = "Подогрев сиденья (уровни/авто).",
        possibleValues = mapOf(
            "SEAT_HEATING_LEVEL_1" to 268763649,
            "SEAT_HEATING_LEVEL_2" to 268763650,
            "SEAT_HEATING_LEVEL_3" to 268763651,
            "SEAT_HEATING_LEVEL_AUTO" to 268763663,
            "SEAT_HEATING_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_SEAT_HEATING_LVLAUTO",
        type = 2,
        key = "HVAC_FUNC_SEAT_HEATING_LVLAUTO",
        value = 269751040,
        description = "Авто‑уровень подогрева сиденья.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_SEAT_MASSAGE",
        type = 2,
        key = "HVAC_FUNC_SEAT_MASSAGE",
        value = 268764928,
        description = "Массаж сиденья (уровни/программы).",
        possibleValues = mapOf(
            "SEAT_MASSAGE_ADJUST" to 759236612,
            "SEAT_MASSAGE_LEVEL_1" to 268764929,
            "SEAT_MASSAGE_LEVEL_2" to 268764930,
            "SEAT_MASSAGE_LEVEL_3" to 268764931,
            "SEAT_MASSAGE_LEVEL_AUTO" to 268764943,
            "SEAT_MASSAGE_OFF" to 0,
            "SEAT_MASSAGE_PROGRAM_1" to 268765953,
            "SEAT_MASSAGE_PROGRAM_2" to 268765954,
            "SEAT_MASSAGE_PROGRAM_3" to 268765955,
            "SEAT_MASSAGE_PROGRAM_4" to 268765956,
            "SEAT_MASSAGE_PROGRAM_5" to 268765957,
            "SEAT_MASSAGE_PROGRAM_6" to 268765958,
            "SEAT_MASSAGE_PROGRAM_7" to 268765959,
            "SEAT_MASSAGE_PROGRAM_8" to 268765960,
            "SEAT_MASSAGE_PROGRAM_9" to 268765961,
            "SEAT_MASSAGE_PROGRAM_A" to 268765962,
            "SEAT_MASSAGE_PROGRAM_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_SEAT_MASSAGE_PROGRAM",
        type = 2,
        key = "HVAC_FUNC_SEAT_MASSAGE_PROGRAM",
        value = 268765952,
        description = "Выбор программы массажа сиденья.",
        possibleValues = mapOf(
            "SEAT_MASSAGE_PROGRAM_1" to 268765953,
            "SEAT_MASSAGE_PROGRAM_2" to 268765954,
            "SEAT_MASSAGE_PROGRAM_3" to 268765955,
            "SEAT_MASSAGE_PROGRAM_4" to 268765956,
            "SEAT_MASSAGE_PROGRAM_5" to 268765957,
            "SEAT_MASSAGE_PROGRAM_6" to 268765958,
            "SEAT_MASSAGE_PROGRAM_7" to 268765959,
            "SEAT_MASSAGE_PROGRAM_8" to 268765960,
            "SEAT_MASSAGE_PROGRAM_9" to 268765961,
            "SEAT_MASSAGE_PROGRAM_A" to 268765962,
            "SEAT_MASSAGE_PROGRAM_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_SEAT_MASSAGE_SWITCH",
        type = 2,
        key = "HVAC_FUNC_SEAT_MASSAGE_SWITCH",
        value = 268765696,
        description = "Вкл/выкл массаж сиденья.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_SEAT_VENTILATION",
        type = 2,
        key = "HVAC_FUNC_SEAT_VENTILATION",
        value = 268763392,
        description = "Вентиляция сиденья (уровни/авто).",
        possibleValues = mapOf(
            "SEAT_VENTILATION_LEVEL_1" to 268763393,
            "SEAT_VENTILATION_LEVEL_2" to 268763394,
            "SEAT_VENTILATION_LEVEL_3" to 268763395,
            "SEAT_VENTILATION_LEVEL_AUTO" to 268763407,
            "SEAT_VENTILATION_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_SEAT_VENTILATION_LVLAUTO",
        type = 2,
        key = "HVAC_FUNC_SEAT_VENTILATION_LVLAUTO",
        value = 269750784,
        description = "Авто‑уровень вентиляции сиденья.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_STEERING_WHEEL_HEAT",
        type = 2,
        key = "HVAC_FUNC_STEERING_WHEEL_HEAT",
        value = 269025536,
        description = "Подогрев рулевого колеса (уровни/авто).",
        possibleValues = mapOf(
            "STEERING_WHEEL_HEAT_AUTO" to 269025551,
            "STEERING_WHEEL_HEAT_HIGH" to 269025539,
            "STEERING_WHEEL_HEAT_LOW" to 269025537,
            "STEERING_WHEEL_HEAT_MID" to 269025538,
            "STEERING_WHEEL_HEAT_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_SWEEPING_MODE",
        type = 2,
        key = "HVAC_FUNC_SWEEPING_MODE",
        value = 268894720,
        description = "Маятниковое перемещение жалюзи (качание).",
        possibleValues = mapOf(
            "SWEEPING_MODE_LEFT_RIGHT" to 268894721,
            "SWEEPING_MODE_LR_AND_UD" to 268894723,
            "SWEEPING_MODE_OFF" to 0,
            "SWEEPING_MODE_UP_DOWN" to 268894722,
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_TEMP",
        type = 2,
        key = "HVAC_FUNC_TEMP",
        value = 268828928,
        description = "Установка требуемой температуры.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_TEMP_DUAL",
        type = 2,
        key = "HVAC_FUNC_TEMP_DUAL",
        value = 268829952,
        description = "Синхронизация/развязка температур по зонам.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_TEMP_HARD_KEY",
        type = 2,
        key = "HVAC_FUNC_TEMP_HARD_KEY",
        value = 268830464,
        description = "Событие клавиши температуры.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_TEMP_MAX",
        type = 2,
        key = "HVAC_FUNC_TEMP_MAX",
        value = 268829184,
        description = "Верхний предел уставки температуры.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_TEMP_MIN",
        type = 2,
        key = "HVAC_FUNC_TEMP_MIN",
        value = 268829440,
        description = "Нижний предел уставки температуры.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_TEMP_OPTIMIZE",
        type = 2,
        key = "HVAC_FUNC_TEMP_OPTIMIZE",
        value = 269615616,
        description = "Оптимизация регулировки температуры (AUTO).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_TEMP_STEP",
        type = 2,
        key = "HVAC_FUNC_TEMP_STEP",
        value = 268829696,
        description = "Шаг изменения уставки температуры.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_TEMP_UNIT",
        type = 2,
        key = "HVAC_FUNC_TEMP_UNIT",
        value = 268830208,
        description = "Единицы температуры для климата.",
        possibleValues = mapOf(
            "TEMPERATURE_UNIT_C" to 268830209,
            "TEMPERATURE_UNIT_F" to 268830210
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_VENTILATION_ONTIME",
        type = 2,
        key = "HVAC_FUNC_VENTILATION_ONTIME",
        value = 269485824,
        description = "Запланированная вентиляция салона по времени.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHybrid.HYBRID_FUNC_BATTERY_CHARGE_MODE",
        type = 2,
        key = "HYBRID_FUNC_BATTERY_CHARGE_MODE",
        value = 604111360,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHybrid.HYBRID_FUNC_BATTERY_SAVE_MODE",
        type = 2,
        key = "HYBRID_FUNC_BATTERY_SAVE_MODE",
        value = 604111104,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHybrid.HYBRID_FUNC_BATTERY_SOC",
        type = 2,
        key = "HYBRID_FUNC_BATTERY_SOC",
        value = 604176640,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISuperHybrid.HYBRID_FUNC_CHARGED_QUANTITY_INFO",
        type = 2,
        key = "HYBRID_FUNC_CHARGED_QUANTITY_INFO",
        value = 826279680,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISuperHybrid.HYBRID_FUNC_CHARGE_IMMEDIATELY",
        type = 2,
        key = "HYBRID_FUNC_CHARGE_IMMEDIATELY",
        value = 826278144,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISuperHybrid.HYBRID_FUNC_CHARGING_DURATION_INFO",
        type = 2,
        key = "HYBRID_FUNC_CHARGING_DURATION_INFO",
        value = 826279936,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISuperHybrid.HYBRID_FUNC_CHARGING_POWER_INFO",
        type = 2,
        key = "HYBRID_FUNC_CHARGING_POWER_INFO",
        value = 826279424,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISuperHybrid.HYBRID_FUNC_DISCHARGING_DURATION_INFO",
        type = 2,
        key = "HYBRID_FUNC_DISCHARGING_DURATION_INFO",
        value = 826280448,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISuperHybrid.HYBRID_FUNC_DISCHARGING_POWER_INFO",
        type = 2,
        key = "HYBRID_FUNC_DISCHARGING_POWER_INFO",
        value = 606080256,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISuperHybrid.HYBRID_FUNC_DISTANCE_PROTECTION_SWITCH",
        type = 2,
        key = "HYBRID_FUNC_DISTANCE_PROTECTION_SWITCH",
        value = 827326720,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHybrid.HYBRID_FUNC_ELECTRIC_AND_HYBRID_SELECT",
        type = 2,
        key = "HYBRID_FUNC_ELECTRIC_AND_HYBRID_SELECT",
        value = 604242176,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHybrid.HYBRID_FUNC_ESTIMD_FU_SAVE",
        type = 2,
        key = "HYBRID_FUNC_ESTIMD_FU_SAVE",
        value = 604242944,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHybrid.HYBRID_FUNC_MAX_EV_MODE",
        type = 2,
        key = "HYBRID_FUNC_MAX_EV_MODE",
        value = 604242432,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHybrid.HYBRID_FUNC_MAX_EV_MODE_POP",
        type = 2,
        key = "HYBRID_FUNC_MAX_EV_MODE_POP",
        value = 604242688,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISuperHybrid.HYBRID_FUNC_PARKING_POWER_GENERATION",
        type = 2,
        key = "HYBRID_FUNC_PARKING_POWER_GENERATION",
        value = 826278400,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHybrid.HYBRID_FUNC_POWER_FLOW",
        type = 2,
        key = "HYBRID_FUNC_POWER_FLOW",
        value = 604045568,
        possibleValues = mapOf(
            "POWER_FLOW_BOOST" to 604045570,
            "POWER_FLOW_CHARGE_AC" to 604045582,
            "POWER_FLOW_CHARGE_DC" to 604045583,
            "POWER_FLOW_DISCHARGE" to 604045584,
            "POWER_FLOW_DRIVEN_BY_ELECTRIC_MOTOR_AND_ENGINE" to 604045592,
            "POWER_FLOW_EAWD" to 604045571,
            "POWER_FLOW_ELEC" to 604045574,
            "POWER_FLOW_ENGINEOFF_REGBRAKE" to 604045578,
            "POWER_FLOW_ENGINEONLY" to 604045572,
            "POWER_FLOW_ENGINEONLY_CHARGE" to 604045573,
            "POWER_FLOW_ENGINEON_REGBRAKE" to 604045579,
            "POWER_FLOW_ENGINEON_REGBRAKE_CHARGE" to 604045580,
            "POWER_FLOW_FRONT_ELE_DRIVE" to 604045586,
            "POWER_FLOW_MAIN_CHARGE" to 604045569,
            "POWER_FLOW_NOT_READY" to 0,
            "POWER_FLOW_PURE_ELE_AWD" to 604045585,
            "POWER_FLOW_REAR_ELE_DRIVE" to 604045587,
            "POWER_FLOW_REGENERATION" to 604045589,
            "POWER_FLOW_SAILING" to 604045581,
            "POWER_FLOW_STANDSTILL" to 604045588,
            "POWER_FLOW_STANDSTILL_AND_BOTH_EM_ENGINE_OFF" to 604045590,
            "POWER_FLOW_STANDSTILL_ENGINE_ON_WITH_ISG" to 604045591,
            "POWER_FLOW_STILL_ENGINEOFF" to 604045575,
            "POWER_FLOW_STILL_ENGINEON" to 604045576,
            "POWER_FLOW_STILL_ENGINEON_CHARGE" to 604045577,
        )
    ),
    PropertyData(
        alias = "ISuperHybrid.HYBRID_FUNC_PT_MOD",
        type = 2,
        key = "HYBRID_FUNC_PT_MOD",
        value = 823132416,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHybrid.HYBRID_FUNC_SMART_ENERGY_MANAGER",
        type = 2,
        key = "HYBRID_FUNC_SMART_ENERGY_MANAGER",
        value = 604242176,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHybrid.HYBRID_FUNC_TOT_FU_SAVE",
        type = 2,
        key = "HYBRID_FUNC_TOT_FU_SAVE",
        value = 604243200,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICarInfo.INT_INFO_ADAS_PADDLE_LANE_CHANGE_ASSIST_AVAILABLE",
        type = 1,
        key = "INT_INFO_ADAS_PADDLE_LANE_CHANGE_ASSIST_AVAILABLE",
        value = 1051904,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICarInfo.INT_INFO_CONFIG_466",
        type = 1,
        key = "INT_INFO_CONFIG_466",
        value = 1052163,
        description = "Параметр конфигурации (код 466).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICarInfo.INT_INFO_CRUISE_CONTROL_CC",
        type = 1,
        key = "INT_INFO_CRUISE_CONTROL_CC",
        value = 1057280,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICarInfo.INT_INFO_CSD_VARIANTS",
        type = 1,
        key = "INT_INFO_CSD_VARIANTS",
        value = 1057024,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICarInfo.INT_INFO_HIGHWAY_ASSIST",
        type = 1,
        key = "INT_INFO_DRIVER_ASSISTANCE_SYSTEM",
        value = 1056768,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICarInfo.INT_INFO_HIGHWAY_ASSIST",
        type = 1,
        key = "INT_INFO_HIGHWAY_ASSIST",
        value = 1052161,
        description = "Конфигурация функции Highway Assist.",
        possibleValues = mapOf(
            "HIGHWAY_ASSIST_AUTO_ALLOWED_HANDS" to 132,
            "HIGHWAY_ASSIST_AUTO_HWA_NOT_HANDS" to 131,
            "HIGHWAY_ASSIST_AUTO_NOT_HANDS" to 130,
            "HIGHWAY_ASSIST_INCLUDED_HANDS" to 4,
            "HIGHWAY_ASSIST_INCLUDED_NOT_HANDS" to 3,
            "HIGHWAY_ASSIST_NAVI_HWA_NOT_HANDS" to 133,
            "HIGHWAY_ASSIST_NOT_AUTO_NOT_HANDS" to 129,
            "HIGHWAY_ASSIST_NOT_INCLUDED_NOT_HANDS" to 2,
        )
    ),
    PropertyData(
        alias = "ICarInfo.INT_INFO_MAINTENANCE_TYPE",
        type = 1,
        key = "INT_INFO_MAINTENANCE_TYPE",
        value = 1051648,
        possibleValues = mapOf(
            "MAINTENANCE_TYPE_REGULAR" to 1051903,
            "MAINTENANCE_TYPE_REGULAR_AND_ENGINE" to 1051649,
        )
    ),
    PropertyData(
        alias = "ICarInfo.INT_INFO_MIC_TOTAL_COUNT",
        type = 1,
        key = "INT_INFO_MIC_TOTAL_COUNT",
        value = 1049856,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICarInfo.INT_INFO_VEHICLE_TYPES",
        type = 1,
        key = "INT_INFO_VEHICLE_TYPES",
        value = 1049088,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.LAMP_EXTERIOR_LIGHT_CONTROL_AHBC",
        type = 2,
        key = "LAMP_EXTERIOR_LIGHT_CONTROL_AHBC",
        value = 537136644,
        description = "Наружный свет: автоматический дальний (AHBC).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.LAMP_EXTERIOR_LIGHT_CONTROL_AUTOMATIC",
        type = 2,
        key = "LAMP_EXTERIOR_LIGHT_CONTROL_AUTOMATIC",
        value = 537136643,
        description = "Наружный свет: автоматический режим.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.LAMP_EXTERIOR_LIGHT_CONTROL_LOWBEAM",
        type = 2,
        key = "LAMP_EXTERIOR_LIGHT_CONTROL_LOWBEAM",
        value = 537136642,
        description = "Наружный свет: ближний свет.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.LAMP_EXTERIOR_LIGHT_CONTROL_OFF",
        type = 2,
        key = "LAMP_EXTERIOR_LIGHT_CONTROL_OFF",
        value = 0,
        description = "Наружный свет: выключено.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.LAMP_EXTERIOR_LIGHT_CONTROL_POS_LIGHT",
        type = 2,
        key = "LAMP_EXTERIOR_LIGHT_CONTROL_POS_LIGHT",
        value = 537136641,
        description = "Наружный свет: габаритные огни.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_APA_SELF_RECOMMENDED",
        type = 2,
        key = "PAS_FUNC_APA_SELF_RECOMMENDED",
        value = 587596032,
        description = "Рекомендация места для автопарковки (APA).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_AUT_PRKG_SLOT_NR_REQ",
        type = 2,
        key = "PAS_FUNC_AUT_PRKG_SLOT_NR_REQ",
        value = 588252672,
        description = "Запрос/выбор номера парковочного места.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_DRVR_ASSC_SYS_BTN_PUSH",
        type = 2,
        key = "PAS_FUNC_DRVR_ASSC_SYS_BTN_PUSH",
        value = 588252416,
        description = "События кнопки ассистента парковки.",
        possibleValues = mapOf(
            "DRVR_ASSC_SYS_BTN_PUSH_ABORT" to 588252428,
            "DRVR_ASSC_SYS_BTN_PUSH_CONFIRM_BTN" to 588252424,
            "DRVR_ASSC_SYS_BTN_PUSH_CONFIRM_PARK_OUT" to 588252426,
            "DRVR_ASSC_SYS_BTN_PUSH_ENTER_APA" to 588252425,
            "DRVR_ASSC_SYS_BTN_PUSH_ENTER_APA_OR_AVM" to 588252422,
            "DRVR_ASSC_SYS_BTN_PUSH_EXIT_APA" to 588252421,
            "DRVR_ASSC_SYS_BTN_PUSH_MANUAL_BTN" to 588252423,
            "DRVR_ASSC_SYS_BTN_PUSH_SELT_APA" to 588252417,
            "DRVR_ASSC_SYS_BTN_PUSH_SELT_RPA" to 588252418,
            "DRVR_ASSC_SYS_BTN_PUSH_START_PARK" to 588252420,
            "DRVR_ASSC_SYS_BTN_PUSH_SUSPEND" to 588252427,
            "DRVR_ASSC_SYS_BTN_PUSH_UNDO_BTN" to 588252419,
        )
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_DRVR_ASSC_SYS_PARK_MOD",
        type = 2,
        key = "PAS_FUNC_DRVR_ASSC_SYS_PARK_MOD",
        value = 588252928,
        description = "Выбор сценария парковки (паралл./перпендикуляр./выезд).",
        possibleValues = mapOf(
            "DRVR_ASSC_SYS_PARK_MOD_CANCEL" to 588252929,
            "DRVR_ASSC_SYS_PARK_MOD_DEFAULT" to 2,
            "DRVR_ASSC_SYS_PARK_MOD_HORIZ_LEFT_PARK_OUT" to 588252937,
            "DRVR_ASSC_SYS_PARK_MOD_HORIZ_PARK_IN" to 588252930,
            "DRVR_ASSC_SYS_PARK_MOD_HORIZ_RIGHT_PARK_OUT" to 588252938,
            "DRVR_ASSC_SYS_PARK_MOD_PERPDIR_LEFT_PARK_OUT_BW" to 588252941,
            "DRVR_ASSC_SYS_PARK_MOD_PERPDIR_LEFT_PARK_OUT_FW" to 588252939,
            "DRVR_ASSC_SYS_PARK_MOD_PERPDIR_PARK_IN" to 588252931,
            "DRVR_ASSC_SYS_PARK_MOD_PERPDIR_PARK_IN_BW" to 588252933,
            "DRVR_ASSC_SYS_PARK_MOD_PERPDIR_PARK_IN_FW" to 588252932,
            "DRVR_ASSC_SYS_PARK_MOD_PERPDIR_RIGHT_PARK_OUT_BW" to 588252942,
            "DRVR_ASSC_SYS_PARK_MOD_PERPDIR_RIGHT_PARK_OUT_FW" to 588252940,
            "DRVR_ASSC_SYS_PARK_MOD_RESERVE_15" to 588252943,
            "DRVR_ASSC_SYS_PARK_MOD_RESERVE_6" to 588252934,
            "DRVR_ASSC_SYS_PARK_MOD_RESERVE_7" to 588252935,
            "DRVR_ASSC_SYS_PARK_MOD_RESERVE_8" to 588252936,
        )
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAC_3DVIEW_LOCK",
        type = 2,
        key = "PAS_FUNC_PAC_3DVIEW_LOCK",
        value = 587404288,
        description = "Фиксация 3D‑вида камеры.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAC_ACTIVATION",
        type = 2,
        key = "PAS_FUNC_PAC_ACTIVATION",
        value = 587399424,
        description = "Активация камер парковочного ассистента.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAC_CAR_MODE_TRANSPARENT",
        type = 2,
        key = "PAS_FUNC_PAC_CAR_MODE_TRANSPARENT",
        value = 587407616,
        description = "Режим «прозрачного шасси».",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAC_STEER_LINK",
        type = 2,
        key = "PAS_FUNC_PAC_STEER_LINK",
        value = 587399680,
        description = "Связка траекторных линий с углом руля.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_ACTIVATED",
        type = 2,
        key = "PAS_FUNC_PAS_ACTIVATED",
        value = 537723136,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_MUTE",
        type = 2,
        key = "PAS_FUNC_PAS_MUTE",
        value = 587268608,
        description = "Отключение звука предупреждений парковочных датчиков.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_TRAILER_PRESENT",
        type = 2,
        key = "PAS_FUNC_PAS_TRAILER_PRESENT",
        value = 587268864,
        description = "Режим прицепа для систем парковки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_VOLUME",
        type = 2,
        key = "PAS_FUNC_PAS_VOLUME",
        value = 537723392,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PRKG_INTRPT_RELD_BTN",
        type = 2,
        key = "PAS_FUNC_PRKG_INTRPT_RELD_BTN",
        value = 588253184,
        description = "Кнопка прерывания процесса парковки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_RCTA_WARNING_VOLUME",
        type = 2,
        key = "PAS_FUNC_RCTA_WARNING_VOLUME",
        value = 587531520,
        description = "Громкость предупреждений RCTA.",
        possibleValues = mapOf(
            "RCTA_WARNING_VOLUME_HIGH" to 587531523,
            "RCTA_WARNING_VOLUME_LOW" to 587531521,
            "RCTA_WARNING_VOLUME_MID" to 587531522,
            "RCTA_WARNING_VOLUME_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_SAP_ACTIVATION",
        type = 2,
        key = "PAS_FUNC_SAP_ACTIVATION",
        value = 587464960,
        description = "Активация функции удалённой/смарт‑парковки (SAP).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISceneMode.SCENE_FUNC_CSD_DRIVER_THEATER_MODE",
        type = 2,
        key = "SCENE_FUNC_CSD_DRIVER_THEATER_MODE",
        value = 788664080,
        description = "Сценарий: «театральный режим» для водителя.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISceneMode.SCENE_FUNC_CSD_PASSENGER_THEATER_MODE",
        type = 2,
        key = "SCENE_FUNC_CSD_PASSENGER_THEATER_MODE",
        value = 788664096,
        description = "Сценарий: «театральный режим» для пассажира.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISceneMode.SCENE_FUNC_NAP_MODE",
        type = 2,
        key = "SCENE_FUNC_NAP_MODE",
        value = 788662272,
        description = "Сценарий: режим сна/отдыха.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISceneMode.SCENE_FUNC_PSD_PASSENGER_THEATER_MODE",
        type = 2,
        key = "SCENE_FUNC_PSD_PASSENGER_THEATER_MODE",
        value = 788664112,
        description = "Сценарий: «театральный режим» (PSD).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISceneMode.SCENE_FUNC_SEAT_ADJMT_REQ",
        type = 2,
        key = "SCENE_FUNC_SEAT_ADJMT_REQ",
        value = 788664320,
        description = "Сценарий: запрос на автонастройку сиденья.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISceneMode.SCENE_FUNC_SEAT_BACK_TARG_POS_AG",
        type = 2,
        key = "SCENE_FUNC_SEAT_BACK_TARG_POS_AG",
        value = 788664576,
        description = "Целевая позиция наклона спинки сиденья (угол).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISceneMode.SCENE_FUNC_SEAT_CUSH_EXT_TARG_POS_PERC",
        type = 2,
        key = "SCENE_FUNC_SEAT_CUSH_EXT_TARG_POS_PERC",
        value = 788665600,
        description = "Целевая длина выдвижной подушки, %.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISceneMode.SCENE_FUNC_SEAT_CUSH_TILT_TARG_POS_PERC",
        type = 2,
        key = "SCENE_FUNC_SEAT_CUSH_TILT_TARG_POS_PERC",
        value = 788665344,
        description = "Целевой наклон подушки, %.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISceneMode.SCENE_FUNC_SEAT_HEI_TARG_POS_PERC",
        type = 2,
        key = "SCENE_FUNC_SEAT_HEI_TARG_POS_PERC",
        value = 788665088,
        description = "Целевая высота сиденья, %.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISceneMode.SCENE_FUNC_SEAT_LEN_TARG_POS_PERC",
        type = 2,
        key = "SCENE_FUNC_SEAT_LEN_TARG_POS_PERC",
        value = 788664832,
        description = "Целевая продольная позиция сиденья, %.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISceneMode.SCENE_FUNC_WASH_MODE",
        type = 2,
        key = "SCENE_FUNC_WASH_MODE",
        value = 788595200,
        description = "Сценарий: режим мойки автомобиля.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_ABS_WARNING",
        type = 3,
        key = "SENSOR_TYPE_ABS_WARNING",
        value = 1058304,
        description = "Индикатор ABS (вкл/выкл/мигает).",
        possibleValues = mapOf(
            "ABS_WARNING_STATE_FLSG" to 1058306,
            "ABS_WARNING_STATE_OFF" to 1058308,
            "ABS_WARNING_STATE_ON" to 1058305,
            "ABS_WARNING_STATE_RESD" to 1058307,
        )
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_ACCELERATOR_DEPTH",
        type = 3,
        key = "SENSOR_TYPE_ACCELERATOR_DEPTH",
        value = 1053696,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_ALRM_STS",
        type = 3,
        key = "SENSOR_TYPE_ALRM_STS",
        value = 2122496,
        description = "Состояние охранной сигнализации.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_AQI_LEVEL_AMBIENT",
        type = 3,
        key = "SENSOR_TYPE_AQI_LEVEL_AMBIENT",
        value = 2106112,
        description = "Качество наружного воздуха (AQI).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_BRAKE_DEPTH",
        type = 3,
        key = "SENSOR_TYPE_BRAKE_DEPTH",
        value = 1053440,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_BRAKE_FLUID_LEVEL",
        type = 3,
        key = "SENSOR_TYPE_BRAKE_FLUID_LEVEL",
        value = 2098688,
        possibleValues = mapOf(
            "BRAKE_FLUID_LEVEL_LOW" to 2098690,
            "BRAKE_FLUID_LEVEL_NORMAL" to 2098689,
            "BRAKE_FLUID_LEVEL_UNKNOWN" to -1,
        )
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_BRK_WARNING",
        type = 3,
        key = "SENSOR_TYPE_BRK_WARNING",
        value = 1058048,
        description = "Индикатор тормозной системы.",
        possibleValues = mapOf(
            "BRK_WARNING_STATE_OFF" to 1058050,
            "BRK_WARNING_STATE_ON" to 1058049,
        )
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_CAR_MODE",
        type = 3,
        key = "SENSOR_TYPE_CAR_MODE",
        value = 2102272,
        possibleValues = mapOf(
            "CAR_MODE_CRASH" to 2102276,
            "CAR_MODE_DYNO" to 2102277,
            "CAR_MODE_FACTORY" to 2102274,
            "CAR_MODE_NORMAL" to 2102273,
            "CAR_MODE_TRANSPORT" to 2102275,
            "CAR_MODE_UNKNOWN" to -1,
        )
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_CAR_SPEED",
        type = 3,
        key = "SENSOR_TYPE_CAR_SPEED",
        value = 1048832,
        description = "Скорость автомобиля (датчик).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_CAR_SPEED_FROM_IPK",
        type = 3,
        key = "SENSOR_TYPE_CAR_SPEED_FROM_IPK",
        value = 1055232,
        description = "Скорость по данным комбинации приборов.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_DAY_NIGHT",
        type = 3,
        key = "SENSOR_TYPE_DAY_NIGHT",
        value = 2101248,
        description = "Режим день/ночь (по датчику света).",
        possibleValues = mapOf(
            "DAY_NIGHT_MODE_DAY" to 2101249,
            "DAY_NIGHT_MODE_NIGHT" to 2101250,
            "DAY_NIGHT_MODE_UNKNOWN" to -1,
        )
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_DRIVER_TIREDNESS_STATUS",
        type = 3,
        key = "SENSOR_TYPE_DRIVER_TIREDNESS_STATUS",
        value = 3149824,
        description = "Индикатор усталости/внимания водителя.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_DRVR_SEAT_BACKREST_PERC",
        type = 3,
        key = "SENSOR_TYPE_DRVR_SEAT_BACKREST_PERC",
        value = 1058560,
        description = "Положение спинки сиденья водителя, %.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_DRVR_SEAT_CUSHION_PERC",
        type = 3,
        key = "SENSOR_TYPE_DRVR_SEAT_CUSHION_PERC",
        value = 1058304,
        description = "Положение подушки сиденья водителя, %.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_DRVR_SEAT_HEIGHT_PERC",
        type = 3,
        key = "SENSOR_TYPE_DRVR_SEAT_HEIGHT_PERC",
        value = 1057792,
        description = "Высота сиденья водителя, %.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_DRVR_SEAT_LENGTH_PERC",
        type = 3,
        key = "SENSOR_TYPE_DRVR_SEAT_LENGTH_PERC",
        value = 1058048,
        description = "Продольное положение сиденья водителя, %.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_ENDURANCE_MILEAGE",
        type = 3,
        key = "SENSOR_TYPE_ENDURANCE_MILEAGE",
        value = 1050624,
        description = "Оценочный запас хода (общий).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_ENDURANCE_MILEAGE_EV",
        type = 3,
        key = "SENSOR_TYPE_ENDURANCE_MILEAGE_EV",
        value = 1054976,
        description = "Оценочный запас хода (EV‑режим).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_ENDURANCE_MILEAGE_FUEL",
        type = 3,
        key = "SENSOR_TYPE_ENDURANCE_MILEAGE_FUEL",
        value = 1054720,
        description = "Оценочный запас хода (на топливе).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_ENGINE_COOLANT_LEVEL",
        type = 3,
        key = "SENSOR_TYPE_ENGINE_COOLANT_LEVEL",
        value = 2098432,
        description = "Уровень охлаждающей жидкости.",
        possibleValues = mapOf(
            "ENGINE_COOLANT_LEVEL_LOW" to 2098434,
            "ENGINE_COOLANT_LEVEL_LOW_1" to 2098435,
            "ENGINE_COOLANT_LEVEL_NORMAL" to 2098433,
            "ENGINE_COOLANT_LEVEL_UNKNOWN" to -1,
        )
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_WARN_TRANSMISSION_TEMP_HIGH",
        type = 3,
        key = "SENSOR_TYPE_ENGINE_COOLANT_TEMPERATURE",
        value = 1052416,
        description = "Температура охлаждающей жидкости (датчик).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_ENGINE_OIL_LEVEL",
        type = 3,
        key = "SENSOR_TYPE_ENGINE_OIL_LEVEL",
        value = 2098176,
        description = "Уровень моторного масла.",
        possibleValues = mapOf(
            "ENGINE_OIL_LEVEL_HIGH" to 2098180,
            "ENGINE_OIL_LEVEL_LOW_1" to 2098178,
            "ENGINE_OIL_LEVEL_LOW_2" to 2098179,
            "ENGINE_OIL_LEVEL_OK" to 2098177,
            "ENGINE_OIL_LEVEL_RESD" to 2098182,
            "ENGINE_OIL_LEVEL_SRVRQRD" to 2098181,
            "ENGINE_OIL_LEVEL_UNKNOWN" to -1,
        )
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_ENGINE_OIL_PERC",
        type = 3,
        key = "SENSOR_TYPE_ENGINE_OIL_PERC",
        value = 1057792,
        description = "Остаточный ресурс моторного масла, %.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_ENGINE_STATE",
        type = 2,
        key = "SENSOR_TYPE_ENGINE_STATE",
        value = 2102784,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_ESC_WARNING",
        type = 3,
        key = "SENSOR_TYPE_ESC_WARNING",
        value = 1058560,
        description = "Индикатор системы стабилизации (ESC).",
        possibleValues = mapOf(
            "ESC_WARNING_STATE_FLSG" to 1058562,
            "ESC_WARNING_STATE_OFF" to 1058564,
            "ESC_WARNING_STATE_ON" to 1058561,
            "ESC_WARNING_STATE_RESD" to 1058563,
        )
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_EV_BATTERY_LEVEL",
        type = 3,
        key = "SENSOR_TYPE_EV_BATTERY_LEVEL",
        value = 1051136,
        description = "Уровень заряда HV‑батареи (SoC).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_FUEL_LEVEL",
        type = 3,
        key = "SENSOR_TYPE_FUEL_LEVEL",
        value = 1050112,
        description = "Уровень топлива (датчик).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_GEAR",
        type = 3,
        key = "SENSOR_TYPE_GEAR",
        value = 2097664,
        description = "Индикация выбранной передачи.",
        possibleValues = mapOf(
            "GEAR_LVL_ONE" to 609225730,
            "GEAR_LVL_THREE" to 609225732,
            "GEAR_LVL_TWO" to 609225731,
            "GEAR_NO_INDICATION" to 609225729,
        )
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_IGNITION_STATE",
        type = 3,
        key = "SENSOR_TYPE_IGNITION_STATE",
        value = 2097408,
        description = "Состояние зажигания.",
        possibleValues = mapOf(
            "IGNITION_STATE_ACC" to 2097412,
            "IGNITION_STATE_DRIVING" to 2097415,
            "IGNITION_STATE_LOCK" to 2097410,
            "IGNITION_STATE_OFF" to 2097411,
            "IGNITION_STATE_ON" to 2097413,
            "IGNITION_STATE_START" to 2097414,
            "IGNITION_STATE_UNDEFINED" to 2097409,
        )
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_LIGHT",
        type = 3,
        key = "SENSOR_TYPE_LIGHT",
        value = 2100992,
        description = "Освещённость (датчик света).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_ODOMETER",
        type = 3,
        key = "SENSOR_TYPE_ODOMETER",
        value = 1050368,
        description = "Пробег (одометр).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_PASS_SEAT_BACKREST_PERC",
        type = 3,
        key = "SENSOR_TYPE_PASS_SEAT_BACKREST_PERC",
        value = 1059584,
        description = "Положение спинки сиденья пассажира, %.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_PASS_SEAT_CUSHION_PERC",
        type = 3,
        key = "SENSOR_TYPE_PASS_SEAT_CUSHION_PERC",
        value = 1059328,
        description = "Положение подушки сиденья пассажира, %.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_PASS_SEAT_HEIGHT_PERC",
        type = 3,
        key = "SENSOR_TYPE_PASS_SEAT_HEIGHT_PERC",
        value = 1058816,
        description = "Высота сиденья пассажира, %.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_PASS_SEAT_LENGTH_PERC",
        type = 3,
        key = "SENSOR_TYPE_PASS_SEAT_LENGTH_PERC",
        value = 1059072,
        description = "Продольное положение сиденья пассажира, %.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_PM25_LEVEL_INDOOR",
        type = 3,
        key = "SENSOR_TYPE_PM25_LEVEL_INDOOR",
        value = 2105856,
        description = "Уровень PM2.5 в салоне.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_RAIN",
        type = 3,
        key = "SENSOR_TYPE_RAIN",
        value = 1052160,
        description = "Уровень сигнала датчика дождя/чувствительности.",
        possibleValues = mapOf(
            "RAINSENSORSENSILVL_LVL1" to 0,
            "RAINSENSORSENSILVL_LVL2" to 1,
            "RAINSENSORSENSILVL_LVL3" to 2,
            "RAINSENSORSENSILVL_LVL4" to 3,
            "RAINSENSORSENSILVL_LVL5" to 4,
            "RAINSENSORSENSILVL_LVL6" to 5,
            "RAINSENSORSENSILVL_LVL7" to 6,
        )
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_SAFE_BELT_DRIVER",
        type = 3,
        key = "SENSOR_TYPE_SAFE_BELT_DRIVER",
        value = 2101760,
        description = "Состояние ремня безопасности водителя.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_SEAT_OCCUPATION_STATUS_DRIVER",
        type = 3,
        key = "SENSOR_TYPE_SEAT_OCCUPATION_STATUS_DRIVER",
        value = 2110208,
        description = "Занятость сиденья водителя.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_SEAT_OCCUPATION_STATUS_PASSENGER",
        type = 3,
        key = "SENSOR_TYPE_SEAT_OCCUPATION_STATUS_PASSENGER",
        value = 2110464,
        description = "Занятость сиденья переднего пассажира.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_SEAT_OCCUPATION_STATUS_SECOND_ROW_LEFT",
        type = 3,
        key = "SENSOR_TYPE_SEAT_OCCUPATION_STATUS_SECOND_ROW_LEFT",
        value = 2110720,
        description = "Занятость второго ряда: левое место.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_SEAT_OCCUPATION_STATUS_SECOND_ROW_RIGHT",
        type = 3,
        key = "SENSOR_TYPE_SEAT_OCCUPATION_STATUS_SECOND_ROW_RIGHT",
        value = 2110976,
        description = "Занятость второго ряда: правое место.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_SNSR_FR_WARNING",
        type = 3,
        key = "SENSOR_TYPE_SNSR_FR_WARNING",
        value = 1058816,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_SNSR_LE_WARNING",
        type = 3,
        key = "SENSOR_TYPE_SNSR_LE_WARNING",
        value = 1059328,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_SNSR_RE_WARNING",
        type = 3,
        key = "SENSOR_TYPE_SNSR_RE_WARNING",
        value = 1059072,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_SNSR_RI_WARNING",
        type = 3,
        key = "SENSOR_TYPE_SNSR_RI_WARNING",
        value = 1059584,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_STEERING_WHEEL_ANGLE",
        type = 3,
        key = "SENSOR_TYPE_STEERING_WHEEL_ANGLE",
        value = 1052672,
        description = "Угол поворота рулевого колеса.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_TEMPERATURE_AMBIENT",
        type = 3,
        key = "SENSOR_TYPE_TEMPERATURE_AMBIENT",
        value = 1051392,
        description = "Температура наружного воздуха.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_TEMPERATURE_INDOOR",
        type = 3,
        key = "SENSOR_TYPE_TEMPERATURE_INDOOR",
        value = 1051648,
        description = "Температура в салоне.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_TIREDNESS_DRIVING_STATE",
        type = 3,
        key = "SENSOR_TYPE_TIREDNESS_DRIVING_STATE",
        value = 3148544,
        description = "Состояние усталости при вождении.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_VEH_MTN_STATE",
        type = 3,
        key = "SENSOR_TYPE_VEH_MTN_STATE",
        value = 3148288,
        description = "Состояние движения/устойчивости авто.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_WARN_ENGINE_COOLANT_SYSTEM_FAULT",
        type = 3,
        key = "SENSOR_TYPE_WARN_ENGINE_COOLANT_SYSTEM_FAULT",
        value = 3148032,
        description = "Предупреждение: неисправность системы охлаждения.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_WARN_ENGINE_COOLANT_TEMP_HIGH",
        type = 3,
        key = "SENSOR_TYPE_WARN_ENGINE_COOLANT_TEMP_HIGH",
        value = 3146752,
        description = "Предупреждение: высокая температура ОЖ.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_WARN_ENGINE_OIL_PRESSURE",
        type = 3,
        key = "SENSOR_TYPE_WARN_ENGINE_OIL_PRESSURE",
        value = 3146496,
        description = "Предупреждение: давление моторного масла.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_WARN_ENGINE_OIL_SYSTEM_FAULT",
        type = 3,
        key = "SENSOR_TYPE_WARN_ENGINE_OIL_SYSTEM_FAULT",
        value = 3147776,
        description = "Предупреждение: неисправность системы смазки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_WARN_EV_BATTERY_LOW",
        type = 3,
        key = "SENSOR_TYPE_WARN_EV_BATTERY_LOW",
        value = 3146240,
        description = "Предупреждение: низкий заряд HV‑батареи.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_WARN_FUEL_RED",
        type = 3,
        key = "SENSOR_TYPE_WARN_FUEL_RED",
        value = 3145984,
        description = "Предупреждение: низкий уровень топлива.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_WARN_TRANSMISSION_TEMP_HIGH",
        type = 3,
        key = "SENSOR_TYPE_WARN_TRANSMISSION_TEMP_HIGH",
        value = 3147008,
        description = "Предупреждение: высокая температура трансмиссии.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_ACC_WITH_TSR",
        type = 2,
        key = "SETTING_FUNC_ACC_WITH_TSR",
        value = 671482624,
        description = "Адаптивный круиз с учётом распознавания знаков (TSR).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_ADAS_PADDLE_LANE_CHANGE_ASSIST",
        type = 2,
        key = "SETTING_FUNC_ADAS_PADDLE_LANE_CHANGE_ASSIST",
        value = 671619840,
        description = "Смена полосы лепестками (вкл/выкл).",
        possibleValues = mapOf(
            "ADAS_PADDLE_LANE_CHANGE_ASSIST_DISABLE" to 1051904,
            "ADAS_PADDLE_LANE_CHANGE_ASSIST_ENABLE" to 1051905,
        )
    ),
    PropertyData(
        alias = "ISafety.SETTING_FUNC_AIRING_WHEN_SMOKING_MODE",
        type = 2,
        key = "SETTING_FUNC_AIRING_WHEN_SMOKING_MODE",
        value = 738395136,
        description = "Режим проветривания при курении.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_AI_ASSIST_DEFAULT_ON",
        type = 2,
        key = "SETTING_FUNC_AI_ASSIST_DEFAULT_ON",
        value = 671613440,
        description = "Включать AI‑ассистента по умолчанию.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_AI_ASSIST_FUSION_NAVI",
        type = 2,
        key = "SETTING_FUNC_AI_ASSIST_FUSION_NAVI",
        value = 671613696,
        description = "Использовать навигацию в работе AI‑ассистента.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_AI_ASSIST_LANE_CHANGE_CONFIRM",
        type = 2,
        key = "SETTING_FUNC_AI_ASSIST_LANE_CHANGE_CONFIRM",
        value = 671614464,
        description = "Требовать подтверждение смены полосы.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_AI_ASSIST_LANE_CHANGE_STRATEGY",
        type = 2,
        key = "SETTING_FUNC_AI_ASSIST_LANE_CHANGE_STRATEGY",
        value = 671614208,
        description = "Стратегия смены полосы (мягкая/стандартная/активная).",
        possibleValues = mapOf(
            "AI_ASSIST_LANE_CHANGE_STRATEGY_GENTLE" to 671614209,
            "AI_ASSIST_LANE_CHANGE_STRATEGY_OFF" to 0,
            "AI_ASSIST_LANE_CHANGE_STRATEGY_RADICAL" to 671614211,
            "AI_ASSIST_LANE_CHANGE_STRATEGY_STANDARD" to 671614210,
        )
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_AI_ASSIST_LANE_CHANGE_WARNING",
        type = 2,
        key = "SETTING_FUNC_AI_ASSIST_LANE_CHANGE_WARNING",
        value = 671614720,
        description = "Тип предупреждения при смене полосы (голос/вибрация/оба).",
        possibleValues = mapOf(
            "AI_ASSIST_LANE_CHANGE_WARNING_BOTH" to 671614723,
            "AI_ASSIST_LANE_CHANGE_WARNING_OFF" to 0,
            "AI_ASSIST_LANE_CHANGE_WARNING_VIBRATE" to 671614722,
            "AI_ASSIST_LANE_CHANGE_WARNING_VOICE" to 671614721,
        )
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_AI_ASSIST_OUT_OVERTAKING_LANE",
        type = 2,
        key = "SETTING_FUNC_AI_ASSIST_OUT_OVERTAKING_LANE",
        value = 671613952,
        description = "Авто‑уход из полосы обгона.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_AI_DRIVER_ASSIST",
        type = 2,
        key = "SETTING_FUNC_AI_DRIVER_ASSIST",
        value = 671613184,
        description = "Главный переключатель AI‑ассистента.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_AMBIENCE_DOOR_OPEN_MUSIC_AUD_TYP",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_DOOR_OPEN_MUSIC_AUD_TYP",
        value = 709886976,
        description = "Тип звука при открытии двери.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_AMBIENCE_LIGHT_BREATHE_COLOR_SET",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_LIGHT_BREATHE_COLOR_SET",
        value = 709886208,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_AMBIENCE_LIGHT_CLIMATE",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_LIGHT_CLIMATE",
        value = 705167872,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_AMBIENCE_LIGHT_COLOR_SET",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_LIGHT_COLOR_SET",
        value = 537528576,
        description = "Выбор цвета атмосферной подсветки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_AMBIENCE_LIGHT_CONTROL_MODE",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_LIGHT_CONTROL_MODE",
        value = 705168896,
        description = "Режим управления подсветкой (цвет/музыка/экран/время).",
        possibleValues = mapOf(
            "AMBIENCE_LIGHT_CONTROL_MODE_COLOR" to 705168900,
            "AMBIENCE_LIGHT_CONTROL_MODE_MORE" to 705168897,
            "AMBIENCE_LIGHT_CONTROL_MODE_MUSIC" to 705168898,
            "AMBIENCE_LIGHT_CONTROL_MODE_SCREEN" to 705168899,
            "AMBIENCE_LIGHT_CONTROL_MODE_TIME" to 705168901,
        )
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_AMBIENCE_LIGHT_EFFECT_SET",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_LIGHT_EFFECT_SET",
        value = 705167616,
        description = "Выбор эффекта подсветки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_AMBIENCE_LIGHT_ENDURANCE_MIL_REMINDER",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_LIGHT_ENDURANCE_MIL_REMINDER",
        value = 704971520,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_AMBIENCE_LIGHT_EXPERIENCE",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_LIGHT_EXPERIENCE",
        value = 537526528,
        description = "Сценарий работы подсветки (полный/кастом).",
        possibleValues = mapOf(
            "AMBIENCE_LIGHT_EXPERIENCE_CUSTOM" to 537526530,
            "AMBIENCE_LIGHT_EXPERIENCE_FULL" to 537526529,
        )
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_AMBIENCE_LIGHT_GOODBYE_SHOW",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_LIGHT_GOODBYE_SHOW",
        value = 704971264,
        description = "Прощальная световая анимация.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_AMBIENCE_LIGHT_ICHARGING_REMIND",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_LIGHT_ICHARGING_REMIND",
        value = 705168128,
        description = "Световое напоминание о зарядке.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_AMBIENCE_LIGHT_INTENSITY_SET",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_LIGHT_INTENSITY_SET",
        value = 704708864,
        description = "Яркость атмосферной подсветки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_AMBIENCE_LIGHT_INTERACTIVE_EFFECT",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_LIGHT_INTERACTIVE_EFFECT",
        value = 537528320,
        description = "Интерактивные эффекты подсветки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_AMBIENCE_LIGHT_MAINCOLOR",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_LIGHT_MAINCOLOR",
        value = 537526784,
        possibleValues = mapOf(
            "AMBIENCE_LIGHT_MAINCOLOR_BREATHE_MODE" to 537526790,
            "AMBIENCE_LIGHT_MAINCOLOR_DRIVERMODE" to 537526786,
            "AMBIENCE_LIGHT_MAINCOLOR_MUSIC" to 537526788,
            "AMBIENCE_LIGHT_MAINCOLOR_NONE" to 0,
            "AMBIENCE_LIGHT_MAINCOLOR_NON_POLAR" to 537526789,
            "AMBIENCE_LIGHT_MAINCOLOR_SETCOLOR" to 537526787,
            "AMBIENCE_LIGHT_MAINCOLOR_SPEED_MODE" to 537526791,
            "AMBIENCE_LIGHT_MAINCOLOR_THEME" to 537526785,
            "AMBIENCE_LIGHT_MAINCOLOR_WEATHER" to 537526792,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_AMBIENCE_LIGHT_MAINZONES",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_LIGHT_MAINZONES",
        value = 537527552,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_AMBIENCE_LIGHT_MUSIC",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_LIGHT_MUSIC",
        value = 704974592,
        description = "Музыкальный режим подсветки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_AMBIENCE_LIGHT_MUSIC_SHOW_MODE",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_LIGHT_MUSIC_SHOW_MODE",
        value = 704972800,
        description = "Режим показа подсветки под музыку.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_AMBIENCE_LIGHT_PHONE_CALL_REMINDER",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_LIGHT_PHONE_CALL_REMINDER",
        value = 704971776,
        description = "Световое напоминание о звонке.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_AMBIENCE_LIGHT_SLIDING_DOOR_REMINDER",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_LIGHT_SLIDING_DOOR_REMINDER",
        value = 704973056,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_AMBIENCE_LIGHT_SOLID_COLOR_SET",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_LIGHT_SOLID_COLOR_SET",
        value = 709885952,
        description = "Выбор фиксированного цвета подсветки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_AMBIENCE_LIGHT_THEME_COLOR",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_LIGHT_THEME_COLOR",
        value = 704709120,
        description = "Выбор темы/цвета подсветки (предустановки).",
        possibleValues = mapOf(
            "AMBIENCE_LIGHT_THEME_COLOR_APPLE_GREEN" to 704709132,
            "AMBIENCE_LIGHT_THEME_COLOR_BLUE" to 704709126,
            "AMBIENCE_LIGHT_THEME_COLOR_GREEN" to 704709124,
            "AMBIENCE_LIGHT_THEME_COLOR_ICE_BLUE" to 704709129,
            "AMBIENCE_LIGHT_THEME_COLOR_INDIGO" to 704709125,
            "AMBIENCE_LIGHT_THEME_COLOR_OFF" to 0,
            "AMBIENCE_LIGHT_THEME_COLOR_ORANGE" to 704709122,
            "AMBIENCE_LIGHT_THEME_COLOR_RED" to 704709121,
            "AMBIENCE_LIGHT_THEME_COLOR_SPANISH_RED" to 704709131,
            "AMBIENCE_LIGHT_THEME_COLOR_SUN_RED" to 704709130,
            "AMBIENCE_LIGHT_THEME_COLOR_VIOLET" to 704709127,
            "AMBIENCE_LIGHT_THEME_COLOR_WHITE" to 704709128,
            "AMBIENCE_LIGHT_THEME_COLOR_YELLOW" to 704709123,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_AMBIENCE_LIGHT_TOPZONES",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_LIGHT_TOPZONES",
        value = 537527296,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_AMBIENCE_LIGHT_VOICE",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_LIGHT_VOICE",
        value = 704974080,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_AMBIENCE_LIGHT_WELCOME_SHOW",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_LIGHT_WELCOME_SHOW",
        value = 704971008,
        description = "Приветственная световая анимация.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_AMBIENCE_LIGHT_WELCOME_SHOW_MODE",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_LIGHT_WELCOME_SHOW_MODE",
        value = 704972544,
        description = "Режим приветственной анимации.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_AMBIENCE_MUSIC_SHOW_PASS_EXCLU",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_MUSIC_SHOW_PASS_EXCLU",
        value = 705169920,
        description = "Исключать пассажирскую сторону из музыкальной анимации.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_AMBIENCE_OPEN_PASS_DOOR_SHOW",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_OPEN_PASS_DOOR_SHOW",
        value = 705169664,
        description = "Световая анимация при открытии двери.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_AMBIENCE_WELCOME_AUD_REQ",
        type = 2,
        key = "SETTING_FUNC_AMBIENCE_WELCOME_AUD_REQ",
        value = 709886720,
        description = "Запрос звукового приветствия.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISafety.SETTING_FUNC_APPROACH_TAIL_UNLOCK",
        type = 2,
        key = "SETTING_FUNC_APPROACH_TAIL_UNLOCK",
        value = 738264320,
        description = "Авторазблокировка багажника при приближении.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISafety.SETTING_FUNC_APPROACH_UNLOCK",
        type = 2,
        key = "SETTING_FUNC_APPROACH_UNLOCK",
        value = 738263296,
        description = "Авторазблокировка при приближении ключа.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_ARTIFICIAL_SOUND_TYPE",
        type = 2,
        key = "SETTING_FUNC_ARTIFICIAL_SOUND_TYPE",
        value = 538575872,
        description = "Тип внешнего звука оповещения (AVAS).",
        possibleValues = mapOf(
            "ARTIFICIAL_SOUND_TYPE_1" to 538575873,
            "ARTIFICIAL_SOUND_TYPE_2" to 538575874,
            "ARTIFICIAL_SOUND_TYPE_3" to 538575875,
            "ARTIFICIAL_SOUND_TYPE_4" to 538575876,
            "ARTIFICIAL_SOUND_TYPE_5" to 538575877,
            "ARTIFICIAL_SOUND_TYPE_6" to 538575878,
            "ARTIFICIAL_SOUND_TYPE_7" to 538575879,
            "ARTIFICIAL_SOUND_TYPE_8" to 538575880,
            "ARTIFICIAL_SOUND_TYPE_NONE" to 0,
        )
    ),
    PropertyData(
        alias = "ISafety.SETTING_FUNC_AUDIBLE_LOCKING_FEEDBACK",
        type = 2,
        key = "SETTING_FUNC_AUDIBLE_LOCKING_FEEDBACK",
        value = 537920256,
        description = "Звуковое подтверждение запирания.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAudio.SETTING_FUNC_AUDIO_SEPARATED",
        type = 2,
        key = "SETTING_FUNC_AUDIO_SEPARATED",
        value = 771948800,
        description = "Разделение аудио (водитель/пассажир).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_AUTONOMOUS_EMERGENCY_BRAKING",
        type = 2,
        key = "SETTING_FUNC_AUTONOMOUS_EMERGENCY_BRAKING",
        value = 537333248,
        description = "Автоматическое экстренное торможение (AEB).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_AUTONOMOUS_EMERGENCY_BRAKING_WARN",
        type = 2,
        key = "SETTING_FUNC_AUTONOMOUS_EMERGENCY_BRAKING_WARN",
        value = 537333249,
        description = "Настройки предупреждения AEB.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_AUTO_CLOSE_ROOF_RAINY",
        type = 2,
        key = "SETTING_FUNC_AUTO_CLOSE_ROOF_RAINY",
        value = 537395968,
        description = "Автозакрытие крыши при дожде.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_AUTO_CLOSE_WINDOW",
        type = 2,
        key = "SETTING_FUNC_AUTO_CLOSE_WINDOW",
        value = 537396224,
        description = "Автозакрытие окон при запирании/удержании.",
        possibleValues = mapOf(
            "AUTO_CLOSE_WINDOW_KEY_LONG_PRESS" to 537396226,
            "AUTO_CLOSE_WINDOW_OFF" to 0,
            "AUTO_CLOSE_WINDOW_VEHICLE_LOCK" to 537396225,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_AUTO_HOLD",
        type = 2,
        key = "SETTING_FUNC_AUTO_HOLD",
        value = 537265152,
        description = "Auto Hold: удержание после остановки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_AUTO_LANE_CHANGE_ASSIST",
        type = 2,
        key = "SETTING_FUNC_AUTO_LANE_CHANGE_ASSIST",
        value = 671351040,
        description = "Ассистент автоматической смены полосы.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_AUTO_REAR_WIPING",
        type = 2,
        key = "SETTING_FUNC_AUTO_REAR_WIPING",
        value = 537657856,
        description = "Автовключение заднего стеклоочистителя.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_AUTO_SHOW_MODE",
        type = 2,
        key = "SETTING_FUNC_AUTO_SHOW_MODE",
        value = 540279296,
        description = "Демонстрационный режим интерфейса (Auto Show).",
        possibleValues = mapOf(
            "SETTING_FUNC_AUTO_SHOW_MODE_TEXT_FALSE" to 1,
            "SETTING_FUNC_AUTO_SHOW_MODE_TEXT_GEAR" to 2,
            "SETTING_FUNC_AUTO_SHOW_MODE_TEXT_NORMAL" to 0,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_AUTO_SHOW_MODE_ENTER_CONDITIONS",
        type = 2,
        key = "SETTING_FUNC_AUTO_SHOW_MODE_ENTER_CONDITIONS",
        value = 540279040,
        description = "Условия входа в демо‑режим.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_AUTO_SHOW_MODE_ICON",
        type = 2,
        key = "SETTING_FUNC_AUTO_SHOW_MODE_ICON",
        value = 540280832,
        description = "Иконка демо‑режима.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_AUTO_SHOW_MODE_POPUP",
        type = 2,
        key = "SETTING_FUNC_AUTO_SHOW_MODE_POPUP",
        value = 540280064,
        description = "Поп‑ап демо‑режима.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_AUTO_SHOW_MODE_TEXT",
        type = 2,
        key = "SETTING_FUNC_AUTO_SHOW_MODE_TEXT",
        value = 540280576,
        description = "Текст/режим отображения в демо‑режиме.",
        possibleValues = mapOf(
            "SETTING_FUNC_AUTO_SHOW_MODE_TEXT_FALSE" to 1,
            "SETTING_FUNC_AUTO_SHOW_MODE_TEXT_GEAR" to 2,
            "SETTING_FUNC_AUTO_SHOW_MODE_TEXT_NORMAL" to 0,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_AUTO_TRAILER_LAMP_CHECK",
        type = 2,
        key = "SETTING_FUNC_AUTO_TRAILER_LAMP_CHECK",
        value = 537135872,
        description = "Автоматическая проверка ламп прицепа.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISafety.SETTING_FUNC_AWAY_LOCK",
        type = 2,
        key = "SETTING_FUNC_AWAY_LOCK",
        value = 738263552,
        description = "Автоблокировка при удалении ключа.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDayMode.SETTING_FUNC_BACKLIGHT_LINKAGE",
        type = 2,
        key = "SETTING_FUNC_BACKLIGHT_LINKAGE",
        value = 687931648,
        description = "Связать яркость с датчиком освещённости/дневным режимом.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_BREATH_SCREEN_MODE",
        type = 2,
        key = "SETTING_FUNC_BREATH_SCREEN_MODE",
        value = 540284928,
        description = "Режим «дыхания» подсветки экрана.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDayMode.SETTING_FUNC_BRIGHTNESS_BACKLIGHT",
        type = 2,
        key = "SETTING_FUNC_BRIGHTNESS_BACKLIGHT",
        value = 687997184,
        description = "Яркость подсветки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDayMode.SETTING_FUNC_BRIGHTNESS_BACKLIGHT_MAX",
        type = 2,
        key = "SETTING_FUNC_BRIGHTNESS_BACKLIGHT_MAX",
        value = 687997440,
        description = "Максимальная яркость подсветки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDayMode.SETTING_FUNC_BRIGHTNESS_BACKLIGHT_MIN",
        type = 2,
        key = "SETTING_FUNC_BRIGHTNESS_BACKLIGHT_MIN",
        value = 687997696,
        description = "Минимальная яркость подсветки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDayMode.SETTING_FUNC_BRIGHTNESS_BACKLIGHT_STEP",
        type = 2,
        key = "SETTING_FUNC_BRIGHTNESS_BACKLIGHT_STEP",
        value = 687997952,
        description = "Шаг изменения яркости подсветки.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_BRIGHTNESS_DAY",
        type = 2,
        key = "SETTING_FUNC_BRIGHTNESS_DAY",
        value = 538247936,
        description = "Яркость дисплея (дневной режим).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICarFunction.CAR_MODULE_DAYMODE",
        type = 2,
        key = "SETTING_FUNC_BRIGHTNESS_DAYMODE",
        value = 688062976,
        description = "Яркость в дневном режиме.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDayMode.SETTING_FUNC_BRIGHTNESS_DIM",
        type = 2,
        key = "SETTING_FUNC_BRIGHTNESS_DIM",
        value = 687998208,
        description = "Яркость (режим DIM).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDayMode.SETTING_FUNC_BRIGHTNESS_DIM_MAX",
        type = 2,
        key = "SETTING_FUNC_BRIGHTNESS_DIM_MAX",
        value = 687998464,
        description = "Максимальная яркость (DIM).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDayMode.SETTING_FUNC_BRIGHTNESS_DIM_MAX",
        type = 2,
        key = "SETTING_FUNC_BRIGHTNESS_DIM_MIN",
        value = 687998720,
        description = "Минимальная яркость (DIM).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDayMode.SETTING_FUNC_BRIGHTNESS_DIM_STEP",
        type = 2,
        key = "SETTING_FUNC_BRIGHTNESS_DIM_STEP",
        value = 687998976,
        description = "Шаг яркости (DIM).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_BRIGHTNESS_MAX",
        type = 2,
        key = "SETTING_FUNC_BRIGHTNESS_MAX",
        value = 538248448,
        description = "Максимальная яркость дисплея.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_BRIGHTNESS_MIN",
        type = 2,
        key = "SETTING_FUNC_BRIGHTNESS_MIN",
        value = 538248704,
        description = "Минимальная яркость дисплея.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_BRIGHTNESS_NIGHT",
        type = 2,
        key = "SETTING_FUNC_BRIGHTNESS_NIGHT",
        value = 538248192,
        description = "Яркость дисплея (ночной режим).",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDayMode.SETTING_FUNC_BRIGHTNESS_SCREEN",
        type = 2,
        key = "SETTING_FUNC_BRIGHTNESS_SCREEN",
        value = 688063744,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_BRIGHTNESS_STEP",
        type = 2,
        key = "SETTING_FUNC_BRIGHTNESS_STEP",
        value = 538248960,
        description = "Шаг яркости дисплея.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAudio.SETTING_FUNC_CAE_SWITCH",
        type = 2,
        key = "SETTING_FUNC_CAE_SWITCH",
        value = 771818240,
        description = "Активное звучание (CAE): вкл/выкл.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ILamp.SETTING_FUNC_CARPET_LIGHT",
        type = 2,
        key = "SETTING_FUNC_CARPET_LIGHT",
        value = 721488640,
        description = "Подсветка зоны у двери: режим/время.",
        possibleValues = mapOf(
            "CARPET_LIGHT_THEME_MODE_1" to 721489153,
            "CARPET_LIGHT_THEME_MODE_2" to 721489154,
            "CARPET_LIGHT_THEME_MODE_3" to 721489155,
            "CARPET_LIGHT_TIME_MODE_45s" to 0,
            "CARPET_LIGHT_TIME_MODE_60s" to 1,
            "CARPET_LIGHT_TIME_MODE_75s" to 2,
            "CARPET_LIGHT_TIME_MODE_90s" to 3,
        )
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_CARPET_LIGHT_SWT",
        type = 2,
        key = "SETTING_FUNC_CARPET_LIGHT_SWT",
        value = 709887232,
        description = "Подсветка зоны у двери: вкл/выкл.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ILamp.SETTING_FUNC_CARPET_LIGHT_THEME",
        type = 2,
        key = "SETTING_FUNC_CARPET_LIGHT_THEME",
        value = 721489152,
        possibleValues = mapOf(
            "CARPET_LIGHT_THEME_MODE_1" to 721489153,
            "CARPET_LIGHT_THEME_MODE_2" to 721489154,
            "CARPET_LIGHT_THEME_MODE_3" to 721489155,
        )
    ),
    PropertyData(
        alias = "ILamp.SETTING_FUNC_CARPET_LIGHT_TIME",
        type = 2,
        key = "SETTING_FUNC_CARPET_LIGHT_TIME",
        value = 721488896,
        possibleValues = mapOf(
            "CARPET_LIGHT_TIME_MODE_45s" to 0,
            "CARPET_LIGHT_TIME_MODE_60s" to 1,
            "CARPET_LIGHT_TIME_MODE_75s" to 2,
            "CARPET_LIGHT_TIME_MODE_90s" to 3,
        )
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_CARPET_LIGHT_TIME_MODE",
        type = 2,
        key = "SETTING_FUNC_CARPET_LIGHT_TIME_MODE",
        value = 709887488,
        possibleValues = mapOf(
            "CARPET_LIGHT_TIME_MODE_45s" to 0,
            "CARPET_LIGHT_TIME_MODE_60s" to 1,
            "CARPET_LIGHT_TIME_MODE_75s" to 2,
            "CARPET_LIGHT_TIME_MODE_90s" to 3,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_CAR_LOCATOR",
        type = 2,
        key = "SETTING_FUNC_CAR_LOCATOR",
        value = 538312960,
        possibleValues = mapOf(
            "CAR_LOCATOR_REMINDER_MODE_LIGHT" to 538313730,
            "CAR_LOCATOR_REMINDER_MODE_LIGHT_SOUND" to 538313731,
            "CAR_LOCATOR_REMINDER_MODE_OFF" to 0,
            "CAR_LOCATOR_REMINDER_MODE_SOUND" to 538313729,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_CAR_LOCATOR_REMINDER_MODE",
        type = 2,
        key = "SETTING_FUNC_CAR_LOCATOR_REMINDER_MODE",
        value = 538313728,
        possibleValues = mapOf(
            "CAR_LOCATOR_REMINDER_MODE_LIGHT" to 538313730,
            "CAR_LOCATOR_REMINDER_MODE_LIGHT_SOUND" to 538313731,
            "CAR_LOCATOR_REMINDER_MODE_OFF" to 0,
            "CAR_LOCATOR_REMINDER_MODE_SOUND" to 538313729,
        )
    ),
    PropertyData(
        alias = "ISafety.SETTING_FUNC_CENTRAL_LOCK",
        type = 2,
        key = "SETTING_FUNC_CENTRAL_LOCK",
        value = 537921792,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISafety.SETTING_FUNC_CHILD_RISKY_BEHAVIOR_MONITOR",
        type = 2,
        key = "SETTING_FUNC_CHILD_RISKY_BEHAVIOR_MONITOR",
        value = 738395392,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDayMode.SETTING_FUNC_CUSTOM_DAY_TIME",
        type = 2,
        key = "SETTING_FUNC_CUSTOM_DAY_TIME",
        value = 688063232,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDayMode.SETTING_FUNC_CUSTOM_NIGHT_TIME",
        type = 2,
        key = "SETTING_FUNC_CUSTOM_NIGHT_TIME",
        value = 688063488,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_DATA_COLLECTION",
        type = 2,
        key = "SETTING_FUNC_DATA_COLLECTION",
        value = 539361792,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_DAYMODE_SETTING",
        type = 2,
        key = "SETTING_FUNC_DAYMODE_SETTING",
        value = 538247424,
        possibleValues = mapOf(
            "DAYMODE_SETTING_AUTO" to 538247427,
            "DAYMODE_SETTING_BRIGHTNESS_AUTO" to 538247427,
            "DAYMODE_SETTING_BRIGHTNESS_DAY" to 538247425,
            "DAYMODE_SETTING_BRIGHTNESS_NIGHT" to 538247426,
            "DAYMODE_SETTING_BRIGHTNESS_OFF" to 0,
            "DAYMODE_SETTING_CUSTOM" to 538247428,
            "DAYMODE_SETTING_DAY" to 538247425,
            "DAYMODE_SETTING_NIGHT" to 538247426,
            "DAYMODE_SETTING_OFF" to 0,
            "DAYMODE_SETTING_SUNRISE_AND_SUNSET" to 538247429,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_DAYMODE_SYNC",
        type = 2,
        key = "SETTING_FUNC_DAYMODE_SYNC",
        value = 538247680,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISafety.SETTING_FUNC_DND_MODE",
        type = 2,
        key = "SETTING_FUNC_DND_MODE",
        value = 738394880,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_DOOR_OPEN_WARN_ACTIVE",
        type = 2,
        key = "SETTING_FUNC_DOOR_OPEN_WARN_ACTIVE",
        value = 538050816,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_DOUBLE_LOCK",
        type = 2,
        key = "SETTING_FUNC_DOUBLE_LOCK",
        value = 539756032,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_DRIVER_PERFOR_SUPPORT",
        type = 2,
        key = "SETTING_FUNC_DRIVER_PERFOR_SUPPORT",
        value = 537003520,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_DRIVER_PERFOR_SUPPORT_REMINDER",
        type = 2,
        key = "SETTING_FUNC_DRIVER_PERFOR_SUPPORT_REMINDER",
        value = 671219968,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.SETTING_FUNC_DRIVE_MODE_KNOB_DIRECTION",
        type = 2,
        key = "SETTING_FUNC_DRIVE_MODE_KNOB_DIRECTION",
        value = 570753792,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.SETTING_FUNC_DRIVE_MODE_KNOB_ROTATE_STEP",
        type = 2,
        key = "SETTING_FUNC_DRIVE_MODE_KNOB_ROTATE_STEP",
        value = 570754048,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.SETTING_FUNC_DRIVE_MODE_REQUEST_NEXT",
        type = 2,
        key = "SETTING_FUNC_DRIVE_MODE_REQUEST_NEXT",
        value = 570753536,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.SETTING_FUNC_DRIVE_MODE_REQUEST_PRE",
        type = 2,
        key = "SETTING_FUNC_DRIVE_MODE_REQUEST_PRE",
        value = 570753280,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_DRIVE_PILOT",
        type = 2,
        key = "SETTING_FUNC_DRIVE_PILOT",
        value = 671548416,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_DRVR_SEB",
        type = 2,
        key = "SETTING_FUNC_DRVR_SEB",
        value = 538379264,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_EASY_INGRESS_EGRESS",
        type = 2,
        key = "SETTING_FUNC_EASY_INGRESS_EGRESS",
        value = 538378496,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_ELECTRIC_MILEAGE_DISPLAY_MODE",
        type = 2,
        key = "SETTING_FUNC_ELECTRIC_MILEAGE_DISPLAY_MODE",
        value = 540281088,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_ELECTRIC_MILEAGE_DISPLAY_SWITCH",
        type = 2,
        key = "SETTING_FUNC_ELECTRIC_MILEAGE_DISPLAY_SWITCH",
        value = 539429632,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_ELECTRONIC_PARKING",
        type = 2,
        key = "SETTING_FUNC_ELECTRONIC_PARKING",
        value = 540148736,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_ELE_SEATBELT_COMFORT",
        type = 2,
        key = "SETTING_FUNC_ELE_SEATBELT_COMFORT",
        value = 537333504,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_EMGY_LANE_KEEP_AID",
        type = 2,
        key = "SETTING_FUNC_EMGY_LANE_KEEP_AID",
        value = 537331200,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_EMGY_LANE_OCC_WARNING",
        type = 2,
        key = "SETTING_FUNC_EMGY_LANE_OCC_WARNING",
        value = 537332480,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_ENERGY_REGENERATION",
        type = 2,
        key = "SETTING_FUNC_ENERGY_REGENERATION",
        value = 537003264,
        possibleValues = mapOf(
            "ENERGY_REGENERATION_LEVEL_AUTO" to 537003268,
            "ENERGY_REGENERATION_LEVEL_HIGH" to 537003267,
            "ENERGY_REGENERATION_LEVEL_LOW" to 537003265,
            "ENERGY_REGENERATION_LEVEL_MID" to 537003266,
            "ENERGY_REGENERATION_LEVEL_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_ENGINE_STOP_START",
        type = 2,
        key = "SETTING_FUNC_ENGINE_STOP_START",
        value = 537002240,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_ENTER_AUTO_SHOW_MODE",
        type = 2,
        key = "SETTING_FUNC_ENTER_AUTO_SHOW_MODE",
        value = 540279808,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_ESC_SPORT_MODE",
        type = 2,
        key = "SETTING_FUNC_ESC_SPORT_MODE",
        value = 537002752,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_ESM_SWITCH",
        type = 2,
        key = "SETTING_FUNC_ESM_SWITCH",
        value = 538575104,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_ESM_TYPE",
        type = 2,
        key = "SETTING_FUNC_ESM_TYPE",
        value = 540281600,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_ESM_VOLUME",
        type = 2,
        key = "SETTING_FUNC_ESM_VOLUME",
        value = 538575360,
        possibleValues = mapOf(
            "ESM_VOLUME_LEVEL_HIGH" to 538575363,
            "ESM_VOLUME_LEVEL_LOW" to 538575361,
            "ESM_VOLUME_LEVEL_MID" to 538575362,
            "ESM_VOLUME_LEVEL_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_EVASIVE_MANEUVER_AID",
        type = 2,
        key = "SETTING_FUNC_EVASIVE_MANEUVER_AID",
        value = 537332736,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_EXTERNAL_ARTIFICIAL_SOUND_TYPE",
        type = 2,
        key = "SETTING_FUNC_EXTERNAL_ARTIFICIAL_SOUND_TYPE",
        value = 538577664,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_FACE_RECOGNITION_RESULT",
        type = 2,
        key = "SETTING_FUNC_FACE_RECOGNITION_RESULT",
        value = 540281344,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_FACIAL_RECOGNITION",
        type = 2,
        key = "SETTING_FUNC_FACIAL_RECOGNITION",
        value = 538706432,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_FORWARD_COLLISION_WARN",
        type = 2,
        key = "SETTING_FUNC_FORWARD_COLLISION_WARN",
        value = 537788672,
        possibleValues = mapOf(
            "FORWARD_COLLISION_WARN_SNVTY_HIGH" to 537788931,
            "FORWARD_COLLISION_WARN_SNVTY_LOW" to 537788929,
            "FORWARD_COLLISION_WARN_SNVTY_NORMAL" to 537788930,
            "FORWARD_COLLISION_WARN_SNVTY_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_FORWARD_COLLISION_WARN_SNVTY",
        type = 2,
        key = "SETTING_FUNC_FORWARD_COLLISION_WARN_SNVTY",
        value = 537788928,
        possibleValues = mapOf(
            "FORWARD_COLLISION_WARN_SNVTY_HIGH" to 537788931,
            "FORWARD_COLLISION_WARN_SNVTY_LOW" to 537788929,
            "FORWARD_COLLISION_WARN_SNVTY_NORMAL" to 537788930,
            "FORWARD_COLLISION_WARN_SNVTY_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_HDC_SWITCH",
        type = 2,
        key = "SETTING_FUNC_HDC_SWITCH",
        value = 537265408,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ILamp.SETTING_FUNC_HEAD_LAMP_HEIGHT_ADJUST",
        type = 2,
        key = "SETTING_FUNC_HEAD_LAMP_HEIGHT_ADJUST",
        value = 721488384,
        possibleValues = mapOf(
            "HEAD_LAMP_HEIGHT_ADJUST_LV1" to 721488385,
            "HEAD_LAMP_HEIGHT_ADJUST_LV2" to 721488386,
            "HEAD_LAMP_HEIGHT_ADJUST_LV3" to 721488387,
            "HEAD_LAMP_HEIGHT_ADJUST_LV4" to 721488388,
            "HEAD_LAMP_HEIGHT_ADJUST_LV5" to 721488389,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_HEAD_RESTRAINT_AUDIO",
        type = 2,
        key = "SETTING_FUNC_HEAD_RESTRAINT_AUDIO",
        value = 539100160,
        possibleValues = mapOf(
            "SETTING_FUNC_HEAD_RESTRAINT_AUDIO_DRVING" to 539099906,
            "SETTING_FUNC_HEAD_RESTRAINT_AUDIO_PRIVATE" to 539099907,
            "SETTING_FUNC_HEAD_RESTRAINT_AUDIO_SHARE" to 539099905,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_HEAD_RESTRAINT_AUDIO_TYPE",
        type = 2,
        key = "SETTING_FUNC_HEAD_RESTRAINT_AUDIO_TYPE",
        value = 539099904,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_HUD_ACTIVE",
        type = 2,
        key = "SETTING_FUNC_HUD_ACTIVE",
        value = 537985280,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHUD.SETTING_FUNC_HUD_ANGLE_ADJUST",
        type = 2,
        key = "SETTING_FUNC_HUD_ANGLE_ADJUST",
        value = 654378752,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ICarInfo.SETTING_FUNC_HUD_ANGLE_RESET",
        type = 2,
        key = "SETTING_FUNC_HUD_ANGLE_RESET",
        value = 654379008,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHUD.SETTING_FUNC_HUD_AR_ENGINE",
        type = 2,
        key = "SETTING_FUNC_HUD_AR_ENGINE",
        value = 654443008,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHUD.SETTING_FUNC_HUD_BRIGHTNESS_ADJUST",
        type = 2,
        key = "SETTING_FUNC_HUD_BRIGHTNESS_ADJUST",
        value = 654378240,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_HUD_CALIBRATION",
        type = 2,
        key = "SETTING_FUNC_HUD_CALIBRATION",
        value = 537985536,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHUD.SETTING_FUNC_HUD_POSITION_ADJUST",
        type = 2,
        key = "SETTING_FUNC_HUD_POSITION_ADJUST",
        value = 654378496,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHUD.SETTING_FUNC_HUD_SNOW_MODE",
        type = 2,
        key = "SETTING_FUNC_HUD_SNOW_MODE",
        value = 654442752,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_HV_BATT_EGY_SOC",
        type = 2,
        key = "SETTING_FUNC_HV_BATT_EGY_SOC",
        value = 605489152,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_HV_BATT_HEAT_POP",
        type = 2,
        key = "SETTING_FUNC_HV_BATT_HEAT_POP",
        value = 538379776,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_HV_BATT_HEAT_TOAST",
        type = 2,
        key = "SETTING_FUNC_HV_BATT_HEAT_TOAST",
        value = 538380032,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAudio.SETTING_FUNC_HXT_SWITCH",
        type = 2,
        key = "SETTING_FUNC_HXT_SWITCH",
        value = 771817984,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_INTELLIGENT_FUEL_SAVE",
        type = 2,
        key = "SETTING_FUNC_INTELLIGENT_FUEL_SAVE",
        value = 538904064,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_INTERNAL_COMMUNICATION",
        type = 2,
        key = "SETTING_FUNC_INTERNAL_COMMUNICATION",
        value = 538902784,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_INTERNAL_COMMUNICATION_VOLUME",
        type = 2,
        key = "SETTING_FUNC_INTERNAL_COMMUNICATION_VOLUME",
        value = 538903040,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_KEYLESS_UNLOCKING",
        type = 2,
        key = "SETTING_FUNC_KEYLESS_UNLOCKING",
        value = 537920512,
        possibleValues = mapOf(
            "KEYLESS_UNLOCKING_ALL_DOORS" to 537920513,
            "KEYLESS_UNLOCKING_OFF" to 0,
            "KEYLESS_UNLOCKING_SINGLE_DOOR" to 537920514,
        )
    ),
    PropertyData(
        alias = "ILamp.SETTING_FUNC_LAMP_ACTIVE_HIGH_BEAM_CONTROL",
        type = 2,
        key = "SETTING_FUNC_LAMP_ACTIVE_HIGH_BEAM_CONTROL",
        value = 721486080,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ILamp.SETTING_FUNC_LAMP_ADAPTIVE_FRONT_LIGHT",
        type = 2,
        key = "SETTING_FUNC_LAMP_ADAPTIVE_FRONT_LIGHT",
        value = 537136384,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ILamp.SETTING_FUNC_LAMP_APPROACH_LIGHT",
        type = 2,
        key = "SETTING_FUNC_LAMP_APPROACH_LIGHT",
        value = 537135360,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ILamp.SETTING_FUNC_LAMP_AUTOMATIC_COURTESY_LIGHT",
        type = 2,
        key = "SETTING_FUNC_LAMP_AUTOMATIC_COURTESY_LIGHT",
        value = 537134592,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_LAMP_BENDINGLIGHT",
        type = 2,
        key = "SETTING_FUNC_LAMP_BENDINGLIGHT",
        value = 537134336,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ILamp.SETTING_FUNC_LAMP_EXTERIOR_LIGHT_CONTROL",
        type = 2,
        key = "SETTING_FUNC_LAMP_EXTERIOR_LIGHT_CONTROL",
        value = 537136640,
        description = "Режим света фар",
        possibleValues = mapOf(
            "LAMP_EXTERIOR_LIGHT_CONTROL_POS_LIGHT" to 537136641,
            "LAMP_EXTERIOR_LIGHT_CONTROL_LOWBEAM" to 537136642,
            "LAMP_EXTERIOR_LIGHT_CONTROL_AUTOMATIC" to 537136643,
            "LAMP_EXTERIOR_LIGHT_CONTROL_AHBC" to 537136644,
            "LAMP_EXTERIOR_LIGHT_CONTROL_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "ILamp.SETTING_FUNC_LAMP_HOME_SAFE_LIGHT",
        type = 2,
        key = "SETTING_FUNC_LAMP_HOME_SAFE_LIGHT",
        value = 537134848,
        possibleValues = mapOf(
            "HOME_SAFE_LIGHT_VALUE_30S" to 537134849,
            "HOME_SAFE_LIGHT_VALUE_60S" to 537134850,
            "HOME_SAFE_LIGHT_VALUE_90S" to 537134851,
            "HOME_SAFE_LIGHT_VALUE_OFF" to 0
        )
    ),
    PropertyData(
        alias = "ILamp.SETTING_FUNC_LAMP_LR_TRAFFIC_LIGHT",
        type = 2,
        key = "SETTING_FUNC_LAMP_LR_TRAFFIC_LIGHT",
        value = 721551616,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_LANE_CHANGE_ASSIST",
        type = 2,
        key = "SETTING_FUNC_LANE_CHANGE_ASSIST",
        value = 537331456,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_LANE_CHANGE_ASSIST_WARNING",
        type = 2,
        key = "SETTING_FUNC_LANE_CHANGE_ASSIST_WARNING",
        value = 671351296,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_LANE_CHANGE_WARING",
        type = 2,
        key = "SETTING_FUNC_LANE_CHANGE_WARING",
        value = 537330432,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_LANE_CHANGE_WARNING_MODE",
        type = 2,
        key = "SETTING_FUNC_LANE_CHANGE_WARNING_MODE",
        value = 537330704,
        possibleValues = mapOf(
            "LANE_CHANGE_WARNING_MODE_OFF" to 0,
            "LANE_CHANGE_WARNING_MODE_SOUND" to 537330706,
            "LANE_CHANGE_WARNING_MODE_VISUAL" to 537330705,
            "LANE_CHANGE_WARNING_MODE_VISUAL_SOUND" to 537330707,
        )
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_LANE_DEPARTURE_WARNING",
        type = 2,
        key = "SETTING_FUNC_LANE_DEPARTURE_WARNING",
        value = 671285504,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_LANE_KEEPING_AID",
        type = 2,
        key = "SETTING_FUNC_LANE_KEEPING_AID",
        value = 537329920,
        possibleValues = mapOf(
            "LANE_KEEPING_AID_MODE_INTV" to 537330178,
            "LANE_KEEPING_AID_MODE_OFF" to 0,
            "LANE_KEEPING_AID_MODE_WARN" to 537330179,
            "LANE_KEEPING_AID_MODE_WARN_INTV" to 537330177,
            "LANE_KEEPING_AID_WARNING_HAPTIC" to 537330946,
            "LANE_KEEPING_AID_WARNING_OFF" to 0,
            "LANE_KEEPING_AID_WARNING_SOUND" to 537330945,
            "LANE_KEEPING_AID_WARNING_SOUND_HAPTIC" to 537330947,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_LANE_KEEPING_AID_MODE",
        type = 2,
        key = "SETTING_FUNC_LANE_KEEPING_AID_MODE",
        value = 537330176,
        possibleValues = mapOf(
            "LANE_KEEPING_AID_MODE_INTV" to 537330178,
            "LANE_KEEPING_AID_MODE_OFF" to 0,
            "LANE_KEEPING_AID_MODE_WARN" to 537330179,
            "LANE_KEEPING_AID_MODE_WARN_INTV" to 537330177,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_LANE_KEEPING_AID_WARNING",
        type = 2,
        key = "SETTING_FUNC_LANE_KEEPING_AID_WARNING",
        value = 537330944,
        possibleValues = mapOf(
            "LANE_KEEPING_AID_WARNING_HAPTIC" to 537330946,
            "LANE_KEEPING_AID_WARNING_OFF" to 0,
            "LANE_KEEPING_AID_WARNING_SOUND" to 537330945,
            "LANE_KEEPING_AID_WARNING_SOUND_HAPTIC" to 537330947,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_LAUNCH_MODE",
        type = 2,
        key = "SETTING_FUNC_LAUNCH_MODE",
        value = 539428864,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_LIFE_DETECTION",
        type = 2,
        key = "SETTING_FUNC_LIFE_DETECTION",
        value = 539427328,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISafety.SETTING_FUNC_LOCK_AUDIO_FEEDBACK",
        type = 2,
        key = "SETTING_FUNC_LOCK_AUDIO_FEEDBACK",
        value = 738396672,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISafety.SETTING_FUNC_LOCK_FEEDBACK_AUDIO_WARNING",
        type = 2,
        key = "SETTING_FUNC_LOCK_FEEDBACK_AUDIO_WARNING",
        value = 738459904,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_MAINTENANCE_MILEAGE_RESET",
        type = 2,
        key = "SETTING_FUNC_MAINTENANCE_MILEAGE_RESET",
        value = 538968320,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_DOOR_LOCK_FAULT",
        type = 2,
        key = "SETTING_FUNC_MCD_AUTO_BRIGHTNESS_SCREEN",
        value = 688063744,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_MIRROR_AUTO_FOLDING",
        type = 2,
        key = "SETTING_FUNC_MIRROR_AUTO_FOLDING",
        value = 537461248,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_MIRROR_DIPPING",
        type = 2,
        key = "SETTING_FUNC_MIRROR_DIPPING",
        value = 537461504,
        description = "Функция автоматического наклона пассажирского зеркала при движении задним ходом, чтобы лучше видеть бордюр/разметку.",
        possibleValues = mapOf(
            "MIRROR_DIPPING_BOTH" to 537461507,
            "MIRROR_DIPPING_DRIVER" to 537461505,
            "MIRROR_DIPPING_OFF" to 0,
            "MIRROR_DIPPING_PASSENGER" to 537461506,
        )
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_MOOD_LIGHT",
        type = 2,
        key = "SETTING_FUNC_MOOD_LIGHT",
        value = 705036544,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_MULTIMEDIA_GESTURE",
        type = 2,
        key = "SETTING_FUNC_MULTIMEDIA_GESTURE",
        value = 539494144,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_MULTI_SEAT_MENU",
        type = 2,
        key = "SETTING_FUNC_MULTI_SEAT_MENU",
        value = 759236608,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_MULTI_SEAT_MENU_HORIZONTAL_POSITION",
        type = 2,
        key = "SETTING_FUNC_MULTI_SEAT_MENU_HORIZONTAL_POSITION",
        value = 759237376,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_MULTI_SEAT_MENU_VERTICAL_POSITION",
        type = 2,
        key = "SETTING_FUNC_MULTI_SEAT_MENU_VERTICAL_POSITION",
        value = 759237120,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_PARK_ASSIST_SYS_ACTIVATED",
        type = 2,
        key = "SETTING_FUNC_PARK_ASSIST_SYS_ACTIVATED",
        value = 537723136,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_PARK_ASSIST_SYS_VOLUME",
        type = 2,
        key = "SETTING_FUNC_PARK_ASSIST_SYS_VOLUME",
        value = 537723392,
        possibleValues = mapOf(
            "PARK_ASSIST_SYS_VOLUME_HIGH" to 537723395,
            "PARK_ASSIST_SYS_VOLUME_LOW" to 537723393,
            "PARK_ASSIST_SYS_VOLUME_MID" to 537723394,
            "PARK_ASSIST_SYS_VOLUME_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_PARK_COMFORT_BELT_DOOR_OPEN",
        type = 2,
        key = "SETTING_FUNC_PARK_COMFORT_BELT_DOOR_OPEN",
        value = 540284416,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_PARK_COMFORT_MODE_COUNTDOWN_TIMER",
        type = 2,
        key = "SETTING_FUNC_PARK_COMFORT_MODE_COUNTDOWN_TIMER",
        value = 540284672,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_PARK_COMFORT_MODE_TIMER",
        type = 2,
        key = "SETTING_FUNC_PARK_COMFORT_MODE_TIMER",
        value = 538837248,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_PARK_COMFORT_MODE_TIMER_MAX",
        type = 2,
        key = "SETTING_FUNC_PARK_COMFORT_MODE_TIMER_MAX",
        value = 538837504,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_PARK_COMFORT_MODE_TIMER_MIN",
        type = 2,
        key = "SETTING_FUNC_PARK_COMFORT_MODE_TIMER_MIN",
        value = 538837760,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_PARK_COMFORT_MODE_TIMER_STEP",
        type = 2,
        key = "SETTING_FUNC_PARK_COMFORT_MODE_TIMER_STEP",
        value = 538838016,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_PASSIVE_ARMING",
        type = 2,
        key = "SETTING_FUNC_PASSIVE_ARMING",
        value = 537921280,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_PBC_AUTO_APPLY",
        type = 2,
        key = "SETTING_FUNC_PBC_AUTO_APPLY",
        value = 537264384,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_PBC_DOUBLE_EPB_SWITCH",
        type = 2,
        key = "SETTING_FUNC_PBC_DOUBLE_EPB_SWITCH",
        value = 537268480,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_PBC_EPB_SWITCH",
        type = 2,
        key = "SETTING_FUNC_PBC_EPB_SWITCH",
        value = 537268224,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_PDC_SWITCH",
        type = 2,
        key = "SETTING_FUNC_PDC_SWITCH",
        value = 537264896,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_PEB_MODE",
        type = 2,
        key = "SETTING_FUNC_PEB_MODE",
        value = 537264640,
        possibleValues = mapOf(
            "PEB_MODE_MSP" to 537264642,
            "PEB_MODE_OFF" to 0,
            "PEB_MODE_PEB" to 537264641,
        )
    ),
    PropertyData(
        alias = "IDriveMode.SETTING_FUNC_PERFORMANCE_SAVING_MODE_VALUE",
        type = 2,
        key = "SETTING_FUNC_PERFORMANCE_SAVING_MODE_VALUE",
        value = 570691328,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_PGEAR_UNLOCK",
        type = 2,
        key = "SETTING_FUNC_PGEAR_UNLOCK",
        value = 540148480,
        possibleValues = mapOf(
            "PGEAR_UNLOCK_TYP_OFF" to 2,
            "PGEAR_UNLOCK_TYP_ON" to 1,
        )
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_PILOT_LANE_CHANGE_ASSIST",
        type = 2,
        key = "SETTING_FUNC_PILOT_LANE_CHANGE_ASSIST",
        value = 671351552,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.SETTING_FUNC_POWER_TRAIN_STOP",
        type = 2,
        key = "SETTING_FUNC_POWER_TRAIN_STOP",
        value = 570691584,
        possibleValues = mapOf(
            "POWER_TRAIN_STOP_EV_BLOCKED" to 570691585,
            "POWER_TRAIN_STOP_EV_PLUS_BLOCKED" to 570691587,
            "POWER_TRAIN_STOP_HEV_BLOCKED" to 570691586,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_PRIVATE_LOCK",
        type = 2,
        key = "SETTING_FUNC_PRIVATE_LOCK",
        value = 537854208,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDayMode.SETTING_FUNC_PSD_BRIGHTNESS_DAYMODE",
        type = 2,
        key = "SETTING_FUNC_PSD_BRIGHTNESS_DAYMODE",
        value = 689963008,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDayMode.SETTING_FUNC_PSD_BRIGHTNESS_SCREEN",
        type = 2,
        key = "SETTING_FUNC_PSD_BRIGHTNESS_SCREEN",
        value = 689963264,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_PSD_SCREEN_SWITCH",
        type = 2,
        key = "SETTING_FUNC_PSD_SCREEN_SWITCH",
        value = 539495936,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISafety.SETTING_FUNC_P_GEAR_UNLOCK",
        type = 2,
        key = "SETTING_FUNC_P_GEAR_UNLOCK",
        value = 738265600,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_RAIN_SENSOR_SENSITIVITY",
        type = 2,
        key = "SETTING_FUNC_RAIN_SENSOR_SENSITIVITY",
        value = 540148224,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_REAR_COLLISION_WARNING",
        type = 2,
        key = "SETTING_FUNC_REAR_COLLISION_WARNING",
        value = 537333760,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_REAR_CROSS_TRAFFIC_ALERT",
        type = 2,
        key = "SETTING_FUNC_REAR_CROSS_TRAFFIC_ALERT",
        value = 537332224,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_REAR_MIRROR_FOLD",
        type = 2,
        key = "SETTING_FUNC_REAR_MIRROR_FOLD",
        value = 539755776,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_REDUCED_GUARD",
        type = 2,
        key = "SETTING_FUNC_REDUCED_GUARD",
        value = 537921536,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_REFUELING_SWT",
        type = 2,
        key = "SETTING_FUNC_REFUELING_SWT",
        value = 538379008,
        possibleValues = mapOf(
            "REFUELING_SWT_UNLCK" to 538379009,
        )
    ),
    PropertyData(
        alias = "IBcm.SETTING_FUNC_REMOTE_DIAGNOSTICS",
        type = 2,
        key = "SETTING_FUNC_REMOTE_DIAGNOSTICS",
        value = 539362048,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_RIS_SWITCH",
        type = 2,
        key = "SETTING_FUNC_RIS_SWITCH",
        value = 671678720,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_ROTATED_WHEELS_WARNING",
        type = 2,
        key = "SETTING_FUNC_ROTATED_WHEELS_WARNING",
        value = 538771968,
        possibleValues = mapOf(
            "ROTATED_WHEELS_WARNING_INFO_NONE" to 0,
            "ROTATED_WHEELS_WARNING_INFO_RIGHTWARD" to 538772226,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_ROTATED_WHEELS_WARNING_INFO",
        type = 2,
        key = "SETTING_FUNC_ROTATED_WHEELS_WARNING_INFO",
        value = 538772224,
        possibleValues = mapOf(
            "ROTATED_WHEELS_WARNING_INFO_NONE" to 0,
            "ROTATED_WHEELS_WARNING_INFO_RIGHTWARD" to 538772226,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_RVDC",
        type = 2,
        key = "SETTING_FUNC_RVDC",
        value = 539361536,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_SAILING_MODE",
        type = 2,
        key = "SETTING_FUNC_SAILING_MODE",
        value = 537003008,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_SCREEN_SAVER_TIME",
        type = 2,
        key = "SETTING_FUNC_SCREEN_SAVER_TIME",
        value = 539035392,
        possibleValues = mapOf(
            "SCREEN_SAVER_TIME_10" to 539035394,
            "SCREEN_SAVER_TIME_5" to 539035393,
            "SCREEN_SAVER_TIME_NEVER" to 539035395,
        )
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_BACKREST",
        type = 2,
        key = "SETTING_FUNC_SEAT_BACKREST",
        value = 755171840,
        possibleValues = mapOf(
            "SEAT_BACKREST_BACKWARD" to 755171842,
            "SEAT_BACKREST_FORWARD" to 755171841,
            "SEAT_BACKREST_OFF" to 0,
            "SEAT_BACKREST_SIDE_ADJUST" to 759236611,
            "SEAT_BACKREST_SIDE_SUPPORT_BACKWARD" to 755237378,
            "SEAT_BACKREST_SIDE_SUPPORT_FORWARD" to 755237377,
            "SEAT_BACKREST_SIDE_SUPPORT_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_BACKREST_POS",
        type = 2,
        key = "SETTING_FUNC_SEAT_BACKREST_POS",
        value = 755172352,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_BACKREST_SIDE_SUPPORT",
        type = 2,
        key = "SETTING_FUNC_SEAT_BACKREST_SIDE_SUPPORT",
        value = 755237376,
        possibleValues = mapOf(
            "SEAT_BACKREST_SIDE_SUPPORT_BACKWARD" to 755237378,
            "SEAT_BACKREST_SIDE_SUPPORT_FORWARD" to 755237377,
            "SEAT_BACKREST_SIDE_SUPPORT_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_CUSHION_EXTENSION",
        type = 2,
        key = "SETTING_FUNC_SEAT_CUSHION_EXTENSION",
        value = 755433728,
        possibleValues = mapOf(
            "SEAT_CUSHION_EXTENSION_BACKWARD" to 755433730,
            "SEAT_CUSHION_EXTENSION_FORWARD" to 755433729,
            "SEAT_CUSHION_EXTENSION_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_CUSHION_TILT",
        type = 2,
        key = "SETTING_FUNC_SEAT_CUSHION_TILT",
        value = 755171584,
        possibleValues = mapOf(
            "SEAT_CUSHION_TILT_DOWN" to 755171586,
            "SEAT_CUSHION_TILT_OFF" to 0,
            "SEAT_CUSHION_TILT_UP" to 755171585,
        )
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_CUSHION_TILT_POS",
        type = 2,
        key = "SETTING_FUNC_SEAT_CUSHION_TILT_POS",
        value = 755172096,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_FOLD",
        type = 2,
        key = "SETTING_FUNC_SEAT_FOLD",
        value = 759236352,
        possibleValues = mapOf(
            "SEAT_FOLD_STATE" to 759236353,
        )
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_HEIGHT",
        type = 2,
        key = "SETTING_FUNC_SEAT_HEIGHT",
        value = 755106304,
        possibleValues = mapOf(
            "SEAT_HEIGHT_DOWN" to 755106306,
            "SEAT_HEIGHT_OFF" to 0,
            "SEAT_HEIGHT_UP" to 755106305,
        )
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_HEIGHT_POS",
        type = 2,
        key = "SETTING_FUNC_SEAT_HEIGHT_POS",
        value = 755106816,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_KNOB",
        type = 2,
        key = "SETTING_FUNC_SEAT_KNOB",
        value = 759237632,
        possibleValues = mapOf(
            "SEAT_KNOB_DOWN" to 759237633,
            "SEAT_KNOB_IDEL" to 759237635,
            "SEAT_KNOB_UP" to 759237634,
        )
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_LEG_SUPPORT_HEIGHT",
        type = 2,
        key = "SETTING_FUNC_SEAT_LEG_SUPPORT_HEIGHT",
        value = 755499264,
        possibleValues = mapOf(
            "SEAT_LEG_SUPPORT_HEIGHT_DOWN" to 755499266,
            "SEAT_LEG_SUPPORT_HEIGHT_OFF" to 0,
            "SEAT_LEG_SUPPORT_HEIGHT_UP" to 755499265,
        )
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_LEG_SUPPORT_HEIGHT_POS",
        type = 2,
        key = "SETTING_FUNC_SEAT_LEG_SUPPORT_HEIGHT_POS",
        value = 755499776,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_LEG_SUPPORT_LENGTH",
        type = 2,
        key = "SETTING_FUNC_SEAT_LEG_SUPPORT_LENGTH",
        value = 755499520,
        possibleValues = mapOf(
            "SEAT_LEG_SUPPORT_LENGTH_BACKWARD" to 755499522,
            "SEAT_LEG_SUPPORT_LENGTH_FORWARD" to 755499521,
            "SEAT_LEG_SUPPORT_LENGTH_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_LEG_SUPPORT_LENGTH_POS",
        type = 2,
        key = "SETTING_FUNC_SEAT_LEG_SUPPORT_LENGTH_POS",
        value = 755500032,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_LENGTH",
        type = 2,
        key = "SETTING_FUNC_SEAT_LENGTH",
        value = 755106048,
        possibleValues = mapOf(
            "SEAT_LENGTH_BACKWARD" to 755106050,
            "SEAT_LENGTH_FORWARD" to 755106049,
            "SEAT_LENGTH_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_LENGTH_POS",
        type = 2,
        key = "SETTING_FUNC_SEAT_LENGTH_POS",
        value = 755106560,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_LUMBAR_EXTENDED",
        type = 2,
        key = "SETTING_FUNC_SEAT_LUMBAR_EXTENDED",
        value = 755368448,
        possibleValues = mapOf(
            "SEAT_LUMBAR_EXTENDED_BACKWARD" to 755368450,
            "SEAT_LUMBAR_EXTENDED_FORWARD" to 755368449,
            "SEAT_LUMBAR_EXTENDED_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_LUMBAR_HEIGHT",
        type = 2,
        key = "SETTING_FUNC_SEAT_LUMBAR_HEIGHT",
        value = 755368192,
        possibleValues = mapOf(
            "SEAT_LUMBAR_HEIGHT_DOWN" to 755368194,
            "SEAT_LUMBAR_HEIGHT_OFF" to 0,
            "SEAT_LUMBAR_HEIGHT_UP" to 755368193,
        )
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_NUM",
        type = 2,
        key = "SETTING_FUNC_SEAT_NUM",
        value = 759238400,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_PHYSIOTHERAPY_PROGRAM",
        type = 2,
        key = "SETTING_FUNC_SEAT_PHYSIOTHERAPY_PROGRAM",
        value = 760218112,
        possibleValues = mapOf(
            "SEAT_PHYSIOTHERAPY_PROGRAM_1" to 760218113,
            "SEAT_PHYSIOTHERAPY_PROGRAM_10" to 760218122,
            "SEAT_PHYSIOTHERAPY_PROGRAM_2" to 760218114,
            "SEAT_PHYSIOTHERAPY_PROGRAM_3" to 760218115,
            "SEAT_PHYSIOTHERAPY_PROGRAM_4" to 760218116,
            "SEAT_PHYSIOTHERAPY_PROGRAM_5" to 760218117,
            "SEAT_PHYSIOTHERAPY_PROGRAM_6" to 760218118,
            "SEAT_PHYSIOTHERAPY_PROGRAM_7" to 760218119,
            "SEAT_PHYSIOTHERAPY_PROGRAM_8" to 760218120,
            "SEAT_PHYSIOTHERAPY_PROGRAM_9" to 760218121,
        )
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_PHYSIOTHERAPY_PROGRAM_ERROR",
        type = 2,
        key = "SETTING_FUNC_SEAT_PHYSIOTHERAPY_PROGRAM_ERROR",
        value = 760219392,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_PHYSIOTHERAPY_SWITCH",
        type = 2,
        key = "SETTING_FUNC_SEAT_PHYSIOTHERAPY_SWITCH",
        value = 760217856,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_POSITION_SAVE",
        type = 2,
        key = "SETTING_FUNC_SEAT_POSITION_SAVE",
        value = 759169280,
        possibleValues = mapOf(
            "SEAT_POSITION_SAVED_1" to 759169281,
            "SEAT_POSITION_SAVED_2" to 759169282,
            "SEAT_POSITION_SAVED_3" to 759169283,
            "SEAT_POSITION_SAVED_4" to 759169284,
            "SEAT_POSITION_SAVED_5" to 759169285,
            "SEAT_POSITION_SAVED_6" to 759169286,
            "SEAT_POSITION_SAVED_7" to 759169287,
            "SEAT_POSITION_SAVED_8" to 759169288,
            "SEAT_POSITION_SAVED_OFF" to 0,
            "SEAT_POSITION_SAVE_AS_1" to 760218881,
            "SEAT_POSITION_SAVE_AS_10" to 760218896,
            "SEAT_POSITION_SAVE_AS_11" to 760218897,
            "SEAT_POSITION_SAVE_AS_2" to 760218882,
            "SEAT_POSITION_SAVE_AS_3" to 760218883,
            "SEAT_POSITION_SAVE_AS_4" to 760218884,
            "SEAT_POSITION_SAVE_AS_5" to 760218885,
            "SEAT_POSITION_SAVE_AS_6" to 760218886,
            "SEAT_POSITION_SAVE_AS_7" to 760218887,
            "SEAT_POSITION_SAVE_AS_8" to 760218888,
            "SEAT_POSITION_SAVE_AS_9" to 760218889,
        )
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_POSITION_SAVE_AS",
        type = 2,
        key = "SETTING_FUNC_SEAT_POSITION_SAVE_AS",
        value = 760218880,
        possibleValues = mapOf(
            "SEAT_POSITION_SAVE_AS_1" to 760218881,
            "SEAT_POSITION_SAVE_AS_10" to 760218896,
            "SEAT_POSITION_SAVE_AS_11" to 760218897,
            "SEAT_POSITION_SAVE_AS_2" to 760218882,
            "SEAT_POSITION_SAVE_AS_3" to 760218883,
            "SEAT_POSITION_SAVE_AS_4" to 760218884,
            "SEAT_POSITION_SAVE_AS_5" to 760218885,
            "SEAT_POSITION_SAVE_AS_6" to 760218886,
            "SEAT_POSITION_SAVE_AS_7" to 760218887,
            "SEAT_POSITION_SAVE_AS_8" to 760218888,
            "SEAT_POSITION_SAVE_AS_9" to 760218889,
        )
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_POSITION_SAVE_AS_RESTORE",
        type = 2,
        key = "SETTING_FUNC_SEAT_POSITION_SAVE_AS_RESTORE",
        value = 760219136,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_POSITION_SET",
        type = 2,
        key = "SETTING_FUNC_SEAT_POSITION_SET",
        value = 759169536,
        possibleValues = mapOf(
            "SETTING_FUNC_SEAT_POSITION_SET_MEMBTN1" to 1,
            "SETTING_FUNC_SEAT_POSITION_SET_MEMBTN2" to 2,
            "SETTING_FUNC_SEAT_POSITION_SET_MEMBTN3" to 3,
            "SETTING_FUNC_SEAT_POSITION_SET_NO" to 0,
        )
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_REST_ALARM_TIME_END",
        type = 2,
        key = "SETTING_FUNC_SEAT_REST_ALARM_TIME_END",
        value = 760218624,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_REST_PATTERN",
        type = 2,
        key = "SETTING_FUNC_SEAT_REST_PATTERN",
        value = 759234816,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISeat.SETTING_FUNC_SEAT_SAVE_RESTORE_POPUP",
        type = 2,
        key = "SETTING_FUNC_SEAT_SAVE_RESTORE_POPUP",
        value = 755041024,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_SEB_POP",
        type = 2,
        key = "SETTING_FUNC_SEB_POP",
        value = 538379520,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_SET_WALLPAPER_TO_DIM",
        type = 2,
        key = "SETTING_FUNC_SET_WALLPAPER_TO_DIM",
        value = 540283136,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAudio.SETTING_FUNC_SOFT_BUTTON_SOUND_TYPE",
        type = 2,
        key = "SETTING_FUNC_SOFT_BUTTON_SOUND_TYPE",
        value = 771883264,
        possibleValues = mapOf(
            "SOFT_BUTTON_SOUND_TYPE_1" to 771883265,
            "SOFT_BUTTON_SOUND_TYPE_2" to 771883266,
            "SOFT_BUTTON_SOUND_TYPE_3" to 771883267,
            "SOFT_BUTTON_SOUND_TYPE_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "ISafety.SETTING_FUNC_SOUND_LOCKING_PROMPT_SWITCH",
        type = 2,
        key = "SETTING_FUNC_SOUND_LOCKING_PROMPT_SWITCH",
        value = 738396416,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAudio.SETTING_FUNC_SOUND_WARNING_VOLUME",
        type = 2,
        key = "SETTING_FUNC_SOUND_WARNING_VOLUME",
        value = 538771712,
        possibleValues = mapOf(
            "SOUND_WARNING_VOLUME_LEVEL_HIGH" to 538771715,
            "SOUND_WARNING_VOLUME_LEVEL_LOW" to 538771713,
            "SOUND_WARNING_VOLUME_LEVEL_MID" to 538771714,
            "SOUND_WARNING_VOLUME_LEVEL_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_SPEED_LIMITATION_MODE",
        type = 2,
        key = "SETTING_FUNC_SPEED_LIMITATION_MODE",
        value = 537068800,
        possibleValues = mapOf(
            "SPEED_LIMITATION_MODE_ASL" to 537068802,
            "SPEED_LIMITATION_MODE_AVSL" to 537068801,
            "SPEED_LIMITATION_MODE_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_SPEED_LIMIT_WARN",
        type = 2,
        key = "SETTING_FUNC_SPEED_LIMIT_WARN",
        value = 671482112,
        possibleValues = mapOf(
            "SPEED_LIMIT_WARNING_MODE_FLASHING" to 671482370,
            "SPEED_LIMIT_WARNING_MODE_NO_WARNING" to 671482369,
            "SPEED_LIMIT_WARNING_MODE_OFF" to 0,
            "SPEED_LIMIT_WARNING_MODE_SOUND" to 671482371,
            "SPEED_LIMIT_WARNING_OFFSET_0KM" to 671482881,
            "SPEED_LIMIT_WARNING_OFFSET_10KM" to 671482883,
            "SPEED_LIMIT_WARNING_OFFSET_5KM" to 671482882,
            "SPEED_LIMIT_WARNING_OFFSET_MINUS_10KM" to 671482885,
            "SPEED_LIMIT_WARNING_OFFSET_MINUS_5KM" to 671482884,
            "SPEED_LIMIT_WARNING_OFFSET_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_SPEED_LIMIT_WARNING_MODE",
        type = 2,
        key = "SETTING_FUNC_SPEED_LIMIT_WARNING_MODE",
        value = 671482368,
        possibleValues = mapOf(
            "SPEED_LIMIT_WARNING_MODE_FLASHING" to 671482370,
            "SPEED_LIMIT_WARNING_MODE_NO_WARNING" to 671482369,
            "SPEED_LIMIT_WARNING_MODE_OFF" to 0,
            "SPEED_LIMIT_WARNING_MODE_SOUND" to 671482371,
        )
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_SPEED_LIMIT_WARNING_OFFSET",
        type = 2,
        key = "SETTING_FUNC_SPEED_LIMIT_WARNING_OFFSET",
        value = 671482880,
        possibleValues = mapOf(
            "SPEED_LIMIT_WARNING_OFFSET_0KM" to 671482881,
            "SPEED_LIMIT_WARNING_OFFSET_10KM" to 671482883,
            "SPEED_LIMIT_WARNING_OFFSET_5KM" to 671482882,
            "SPEED_LIMIT_WARNING_OFFSET_MINUS_10KM" to 671482885,
            "SPEED_LIMIT_WARNING_OFFSET_MINUS_5KM" to 671482884,
            "SPEED_LIMIT_WARNING_OFFSET_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_SPEED_LIMIT_WARNING_OFFSET_VALUE",
        type = 2,
        key = "SETTING_FUNC_SPEED_LIMIT_WARNING_OFFSET_VALUE",
        value = 671483136,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_SPEED_LIMIT_WARNING_OFFSET_VALUE_MAX",
        type = 2,
        key = "SETTING_FUNC_SPEED_LIMIT_WARNING_OFFSET_VALUE_MAX",
        value = 671483392,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_SPEED_LIMIT_WARNING_OFFSET_VALUE_MIN",
        type = 2,
        key = "SETTING_FUNC_SPEED_LIMIT_WARNING_OFFSET_VALUE_MIN",
        value = 671483648,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_SPEED_LIMIT_WARNING_OFFSET_VALUE_STEP",
        type = 2,
        key = "SETTING_FUNC_SPEED_LIMIT_WARNING_OFFSET_VALUE_STEP",
        value = 671483904,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_SPEED_LIMIT_WARNING_OFFSET_VALUE_SWITCH",
        type = 2,
        key = "SETTING_FUNC_SPEED_LIMIT_WARNING_OFFSET_VALUE_SWITCH",
        value = 671484160,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_STEERING_ASSISTANCE_LEVEL",
        type = 2,
        key = "SETTING_FUNC_STEERING_ASSISTANCE_LEVEL",
        value = 537331712,
        possibleValues = mapOf(
            "STEERING_ASSISTANCE_LEVEL_HIGH" to 537331713,
            "STEERING_ASSISTANCE_LEVEL_LOW" to 537331715,
            "STEERING_ASSISTANCE_LEVEL_MEDIUM" to 537331714,
            "STEERING_ASSISTANCE_LEVEL_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_SUSPENSION_DEACTIVATION_DAMPENING",
        type = 2,
        key = "SETTING_FUNC_SUSPENSION_DEACTIVATION_DAMPENING",
        value = 538509824,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_SUSPENSION_DRIVER_ENTRY_CONTROL",
        type = 2,
        key = "SETTING_FUNC_SUSPENSION_DRIVER_ENTRY_CONTROL",
        value = 538510080,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_SUSPENSION_HEIGHT_ADJUST",
        type = 2,
        key = "SETTING_FUNC_SUSPENSION_HEIGHT_ADJUST",
        value = 538509568,
        possibleValues = mapOf(
            "SUSPENSION_HEIGHT_ADJUST_LEVEL_HIGH_1" to 538509570,
            "SUSPENSION_HEIGHT_ADJUST_LEVEL_HIGH_2" to 538509569,
            "SUSPENSION_HEIGHT_ADJUST_LEVEL_LOW_1" to 538509572,
            "SUSPENSION_HEIGHT_ADJUST_LEVEL_LOW_2" to 538509573,
            "SUSPENSION_HEIGHT_ADJUST_LEVEL_NORMAL" to 538509571,
            "SUSPENSION_HEIGHT_ADJUST_LEVEL_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_TCAM_RESET",
        type = 2,
        key = "SETTING_FUNC_TCAM_RESET",
        value = 538314240,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SETTING_FUNC_TELM_PHOTO_SWT",
        type = 3,
        key = "SETTING_FUNC_TELM_PHOTO_SWT",
        value = 2123520,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISafety.SETTING_FUNC_THINGS_LEFT_REMIND",
        type = 2,
        key = "SETTING_FUNC_THINGS_LEFT_REMIND",
        value = 738395648,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_THREE_SCREEN_ANIMATION",
        type = 2,
        key = "SETTING_FUNC_THREE_SCREEN_ANIMATION",
        value = 540148992,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_TRAFFIC_LIGHT_ATTENTION",
        type = 2,
        key = "SETTING_FUNC_TRAFFIC_LIGHT_ATTENTION",
        value = 537332992,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_TRAFFIC_LIGHT_ATTENTION_SOUND",
        type = 2,
        key = "SETTING_FUNC_TRAFFIC_LIGHT_ATTENTION_SOUND",
        value = 671154432,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_TRAFFIC_SIGN_RECOGNITION",
        type = 2,
        key = "SETTING_FUNC_TRAFFIC_SIGN_RECOGNITION",
        value = 537592064,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_TRAILER_MODE",
        type = 2,
        key = "SETTING_FUNC_TRAILER_MODE",
        value = 537268736,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_TRANSITION_END_COLOR",
        type = 2,
        key = "SETTING_FUNC_TRANSITION_END_COLOR",
        value = 705102592,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_TRANSITION_MODE",
        type = 2,
        key = "SETTING_FUNC_TRANSITION_MODE",
        value = 705102080,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IAmbienceLight.SETTING_FUNC_TRANSITION_START_COLOR",
        type = 2,
        key = "SETTING_FUNC_TRANSITION_START_COLOR",
        value = 705102336,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISafety.SETTING_FUNC_TRUNK_OPENING_PERCENTAGE",
        type = 2,
        key = "SETTING_FUNC_TRUNK_OPENING_PERCENTAGE",
        value = 738395904,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISafety.SETTING_FUNC_TRUNK_OPENING_POSITION",
        type = 2,
        key = "SETTING_FUNC_TRUNK_OPENING_POSITION",
        value = 738265088,
        possibleValues = mapOf(
            "TRUNK_OPENING_POSITION_LEVEL_1" to 738265089,
            "TRUNK_OPENING_POSITION_LEVEL_2" to 738265090,
            "TRUNK_OPENING_POSITION_LEVEL_3" to 738265091,
            "TRUNK_OPENING_POSITION_LEVEL_4" to 738265092,
            "TRUNK_OPENING_POSITION_LEVEL_5" to 738265093,
            "TRUNK_OPENING_POSITION_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "ISafety.SETTING_FUNC_TRUNK_STATE",
        type = 2,
        key = "SETTING_FUNC_TRUNK_STATE",
        value = 738330112,
        possibleValues = mapOf(
            "TRUNK_STATE_FULL_CLOSE" to 738330114,
            "TRUNK_STATE_FULL_OPEN" to 738330118,
            "TRUNK_STATE_HALF_CLOSE" to 738330128,
            "TRUNK_STATE_MOVE_DOWN" to 738330119,
            "TRUNK_STATE_MOVE_DOWN_BREAK" to 738330120,
            "TRUNK_STATE_MOVE_UP" to 738330115,
            "TRUNK_STATE_MOVE_UP_BREAK" to 738330116,
            "TRUNK_STATE_STOP_DURING_CLOSE" to 738330121,
            "TRUNK_STATE_STOP_DURING_OPEN" to 738330117,
            "TRUNK_STATE_STOP_MIN_POSITION" to 738330129,
            "TRUNK_STATE_UNKNOW" to 738330113,
        )
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_TTS_ASY_EYES_OFF_WARN_RQRD_SOUND",
        type = 2,
        key = "SETTING_FUNC_TTS_ASY_EYES_OFF_WARN_RQRD_SOUND",
        value = 671746048,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_TTS_ASY_LAN_CHG_REMINDER",
        type = 2,
        key = "SETTING_FUNC_TTS_ASY_LAN_CHG_REMINDER",
        value = 671745280,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_TWOSTEP_UNLOCKING",
        type = 2,
        key = "SETTING_FUNC_TWOSTEP_UNLOCKING",
        value = 537922048,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_VEHICLE_ENGINE_TRAVLLED_DISTANCE",
        type = 2,
        key = "SETTING_FUNC_VEHICLE_ENGINE_TRAVLLED_DISTANCE",
        value = 541066496,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_VEHICLE_EV_TRAVLLED_DISTANCE",
        type = 2,
        key = "SETTING_FUNC_VEHICLE_EV_TRAVLLED_DISTANCE",
        value = 541066240,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_VEHICLE_OIL_TOTAL_TRAVLLED_DISTANCE",
        type = 2,
        key = "SETTING_FUNC_VEHICLE_OIL_TOTAL_TRAVLLED_DISTANCE",
        value = 541065472,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_VEHICLE_OIL_USING_DAYS",
        type = 2,
        key = "SETTING_FUNC_VEHICLE_OIL_USING_DAYS",
        value = 541065728,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_VOICE_BRST_MODE",
        type = 2,
        key = "SETTING_FUNC_VOICE_BRST_MODE",
        value = 671748352,
        possibleValues = mapOf(
            "VOICE_BRST_MODE_DETAIL" to 671748353,
            "VOICE_BRST_MODE_STREAM_LINE" to 671748354,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_VOICE_CONTROL_LOCKING",
        type = 2,
        key = "SETTING_FUNC_VOICE_CONTROL_LOCKING",
        value = 540284160,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SETTING_FUNC_VSTD_CTRL_REQ",
        type = 3,
        key = "SETTING_FUNC_VSTD_CTRL_REQ",
        value = 2123264,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_VSTD_FAIL_TO_OPEN_TELM",
        type = 2,
        key = "SETTING_FUNC_VSTD_FAIL_TO_OPEN_TELM",
        value = 536953600,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_VSTD_VFC_VEHICLE_SENTRY_FT_DET_CH",
        type = 2,
        key = "SETTING_FUNC_VSTD_VFC_VEHICLE_SENTRY_FT_DET_CH",
        value = 536954368,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_VSTD_VFC_VEHICLE_SENTRY_FT_DET_PS",
        type = 2,
        key = "SETTING_FUNC_VSTD_VFC_VEHICLE_SENTRY_FT_DET_PS",
        value = 536954624,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_VSTD_VFC_VEHICLE_SENTRY_FT_DET_SV",
        type = 2,
        key = "SETTING_FUNC_VSTD_VFC_VEHICLE_SENTRY_FT_DET_SV",
        value = 536954880,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ILamp.SETTING_FUNC_WELCOME_LIGHT",
        type = 2,
        key = "SETTING_FUNC_WELCOME_LIGHT",
        value = 721617152,
        possibleValues = mapOf(
            "WELCOME_LIGHT_MODE_1" to 721617409,
            "WELCOME_LIGHT_MODE_2" to 721617410,
            "WELCOME_LIGHT_MODE_3" to 721617411,
            "WELCOME_LIGHT_MODE_4" to 721617412,
            "WELCOME_LIGHT_MODE_5" to 721617413,
            "WELCOME_LIGHT_MODE_6" to 721617414,
            "WELCOME_LIGHT_MODE_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "ILamp.SETTING_FUNC_WELCOME_LIGHT_MODE",
        type = 2,
        key = "SETTING_FUNC_WELCOME_LIGHT_MODE",
        value = 721617408,
        possibleValues = mapOf(
            "WELCOME_LIGHT_MODE_1" to 721617409,
            "WELCOME_LIGHT_MODE_2" to 721617410,
            "WELCOME_LIGHT_MODE_3" to 721617411,
            "WELCOME_LIGHT_MODE_4" to 721617412,
            "WELCOME_LIGHT_MODE_5" to 721617413,
            "WELCOME_LIGHT_MODE_6" to 721617414,
            "WELCOME_LIGHT_MODE_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_WINDOW_CLOSE_SUNCURTAIN",
        type = 2,
        key = "SETTING_FUNC_WINDOW_CLOSE_SUNCURTAIN",
        value = 537395456,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_WINDOW_VENTILATE",
        type = 2,
        key = "SETTING_FUNC_WINDOW_VENTILATE",
        value = 537396736,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_WINDSCREEN_SERVICE_POSITION",
        type = 2,
        key = "SETTING_FUNC_WINDSCREEN_SERVICE_POSITION",
        value = 537657600,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_TYPE_VSTD_MODE_STS",
        type = 2,
        key = "SETTING_TYPE_VSTD_MODE_STS",
        value = 536951296,
        possibleValues = mapOf(
            "SETTING_VALUE_VSTD_MODE_STS_OFF" to 536951297,
            "SETTING_VALUE_VSTD_MODE_STS_STANDBY" to 536951298,
            "SETTING_VALUE_VSTD_MODE_STS_ON" to 536951299,
            "SETTING_VALUE_VSTD_MODE_STS_ALERT" to 536951300,
            "SETTING_VALUE_VSTD_MODE_STS_ALARM" to 536951301
        )
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_FLAT_STATE",
        type = 3,
        key = "TIRE_FLAT_STATE",
        value = 5263360,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_MSG_FLAG_FRONT_LEFT",
        type = 3,
        key = "TIRE_MSG_FLAG_FRONT_LEFT",
        value = 5249280,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_MSG_FLAG_FRONT_RIGHT",
        type = 3,
        key = "TIRE_MSG_FLAG_FRONT_RIGHT",
        value = 5251072,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_MSG_FLAG_REAR_LEFT",
        type = 3,
        key = "TIRE_MSG_FLAG_REAR_LEFT",
        value = 5251328,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_MSG_FLAG_REAR_RIGHT",
        type = 3,
        key = "TIRE_MSG_FLAG_REAR_RIGHT",
        value = 5251584,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_PRESSURE_FRONT_LEFT",
        type = 3,
        key = "TIRE_PRESSURE_FRONT_LEFT",
        value = 5243136,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_PRESSURE_FRONT_RIGHT",
        type = 3,
        key = "TIRE_PRESSURE_FRONT_RIGHT",
        value = 5243392,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_PRESSURE_REAR_LEFT",
        type = 3,
        key = "TIRE_PRESSURE_REAR_LEFT",
        value = 5243648,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_PRESSURE_REAR_RIGHT",
        type = 3,
        key = "TIRE_PRESSURE_REAR_RIGHT",
        value = 5243904,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_SENSOR_BATTERY_LOW_STATE",
        type = 3,
        key = "TIRE_SENSOR_BATTERY_LOW_STATE",
        value = 5275648,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_SENSOR_STATES_FRONT_LEFT",
        type = 3,
        key = "TIRE_SENSOR_STATES_FRONT_LEFT",
        value = 5248256,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_SENSOR_STATES_FRONT_RIGHT",
        type = 3,
        key = "TIRE_SENSOR_STATES_FRONT_RIGHT",
        value = 5248512,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_SENSOR_STATES_REAR_LEFT",
        type = 3,
        key = "TIRE_SENSOR_STATES_REAR_LEFT",
        value = 5248768,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_SENSOR_STATES_REAR_RIGHT",
        type = 3,
        key = "TIRE_SENSOR_STATES_REAR_RIGHT",
        value = 5249024,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_SYSTEM_FAILURE_STATE",
        type = 3,
        key = "TIRE_SYSTEM_FAILURE_STATE",
        value = 5271552,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_TEMPERATURE_FRONT_LEFT",
        type = 3,
        key = "TIRE_TEMPERATURE_FRONT_LEFT",
        value = 5244160,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_TEMPERATURE_FRONT_RIGHT",
        type = 3,
        key = "TIRE_TEMPERATURE_FRONT_RIGHT",
        value = 5244416,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_TEMPERATURE_REAR_LEFT",
        type = 3,
        key = "TIRE_TEMPERATURE_REAR_LEFT",
        value = 5244672,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_TEMPERATURE_REAR_RIGHT",
        type = 3,
        key = "TIRE_TEMPERATURE_REAR_RIGHT",
        value = 5244928,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_TEMPERATURE_STATE",
        type = 3,
        key = "TIRE_TEMPERATURE_STATE",
        value = 5267456,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_TPMS_SYS_STATES",
        type = 3,
        key = "TIRE_TPMS_SYS_STATES",
        value = 5259264,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_WARNING_FRONT_LEFT",
        type = 3,
        key = "TIRE_WARNING_FRONT_LEFT",
        value = 5245184,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_WARNING_FRONT_LEFT_QUICKLEAKING",
        type = 3,
        key = "TIRE_WARNING_FRONT_LEFT_QUICKLEAKING",
        value = 5247232,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_WARNING_FRONT_LEFT_TEMPERATURE",
        type = 3,
        key = "TIRE_WARNING_FRONT_LEFT_TEMPERATURE",
        value = 5246208,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_WARNING_FRONT_RIGHT",
        type = 3,
        key = "TIRE_WARNING_FRONT_RIGHT",
        value = 5245440,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_WARNING_FRONT_RIGHT_QUICKLEAKING",
        type = 3,
        key = "TIRE_WARNING_FRONT_RIGHT_QUICKLEAKING",
        value = 5247488,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_WARNING_FRONT_RIGHT_TEMPERATURE",
        type = 3,
        key = "TIRE_WARNING_FRONT_RIGHT_TEMPERATURE",
        value = 5246464,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_WARNING_REAR_LEFT",
        type = 3,
        key = "TIRE_WARNING_REAR_LEFT",
        value = 5245696,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_WARNING_REAR_LEFT_QUICKLEAKING",
        type = 3,
        key = "TIRE_WARNING_REAR_LEFT_QUICKLEAKING",
        value = 5247744,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_WARNING_REAR_LEFT_TEMPERATURE",
        type = 3,
        key = "TIRE_WARNING_REAR_LEFT_TEMPERATURE",
        value = 5246720,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_WARNING_REAR_RIGHT",
        type = 3,
        key = "TIRE_WARNING_REAR_RIGHT",
        value = 5245952,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_WARNING_REAR_RIGHT_QUICKLEAKING",
        type = 3,
        key = "TIRE_WARNING_REAR_RIGHT_QUICKLEAKING",
        value = 5248000,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITireSensor.TIRE_WARNING_REAR_RIGHT_TEMPERATURE",
        type = 3,
        key = "TIRE_WARNING_REAR_RIGHT_TEMPERATURE",
        value = 5246976,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITripData.TRIP_DC_AVERAGE_FUEL_CONSUMPTION",
        type = 2,
        key = "TRIP_DC_AVERAGE_FUEL_CONSUMPTION",
        value = 612369924,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITripData.TRIP_DC_AVERAGE_POWER_CONSUMPTION",
        type = 2,
        key = "TRIP_DC_AVERAGE_POWER_CONSUMPTION",
        value = 612369925,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITripData.TRIP_DC_AVERAGE_SPEED",
        type = 2,
        key = "TRIP_DC_AVERAGE_SPEED",
        value = 612369922,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITripData.TRIP_DC_SUBTOTAL_MILEAGE",
        type = 2,
        key = "TRIP_DC_SUBTOTAL_MILEAGE",
        value = 612369921,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITripData.TRIP_DC_TRAVEL_TIME",
        type = 2,
        key = "TRIP_DC_TRAVEL_TIME",
        value = 612369923,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITripData.TRIP_FUNC_AIRCDNEGY_DISTBN",
        type = 2,
        key = "TRIP_FUNC_AIRCDNEGY_DISTBN",
        value = 612370944,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITripData.TRIP_FUNC_AUTO_RESET_OPTION",
        type = 2,
        key = "TRIP_FUNC_AUTO_RESET_OPTION",
        value = 612369152,
        possibleValues = mapOf(
            "AUTO_RESET_OPTION_4_HOURS" to 612369153,
            "AUTO_RESET_OPTION_CHARGING" to 612369154,
            "AUTO_RESET_OPTION_PARKING" to 612369156,
            "AUTO_RESET_OPTION_PARKING_OIL" to 612369155,
        )
    ),
    PropertyData(
        alias = "ITripData.TRIP_FUNC_AVERAGE_CONSUME_100",
        type = 2,
        key = "TRIP_FUNC_AVERAGE_CONSUME_100",
        value = 612372480,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITripData.TRIP_FUNC_AVERAGE_CONSUME_50",
        type = 2,
        key = "TRIP_FUNC_AVERAGE_CONSUME_50",
        value = 612371968,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITripData.TRIP_FUNC_AVERAGE_EN_CONSUME_100",
        type = 2,
        key = "TRIP_FUNC_AVERAGE_EN_CONSUME_100",
        value = 612372992,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITripData.TRIP_FUNC_AVERAGE_EN_CONSUME_50",
        type = 2,
        key = "TRIP_FUNC_AVERAGE_EN_CONSUME_50",
        value = 612372736,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITripData.TRIP_FUNC_BATTTHERMEGY_DISTBN",
        type = 2,
        key = "TRIP_FUNC_BATTTHERMEGY_DISTBN",
        value = 612371200,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITripData.TRIP_FUNC_CURRENT_TRIP_RESET",
        type = 2,
        key = "TRIP_FUNC_CURRENT_TRIP_RESET",
        value = 612370432,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITripData.TRIP_FUNC_DIM_UI_SWITCH",
        type = 2,
        key = "TRIP_FUNC_DIM_UI_SWITCH",
        value = 612370176,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITripData.TRIP_FUNC_DRIVING_COMPUTER",
        type = 2,
        key = "TRIP_FUNC_DRIVING_COMPUTER",
        value = 612369920,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITripData.TRIP_FUNC_DRVREGY_DISTBN",
        type = 2,
        key = "TRIP_FUNC_DRVREGY_DISTBN",
        value = 612370688,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITripData.TRIP_FUNC_ENERGY_RESET",
        type = 2,
        key = "TRIP_FUNC_ENERGY_RESET",
        value = 612371712,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITripData.TRIP_FUNC_OTHEREGY_DISTBN",
        type = 2,
        key = "TRIP_FUNC_OTHEREGY_DISTBN",
        value = 612371456,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITripData.TRIP_FUNC_RESET",
        type = 2,
        key = "TRIP_FUNC_RESET",
        value = 612368896,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ITripData.TRIP_FUNC_TRIP_RNG_SWT",
        type = 2,
        key = "TRIP_FUNC_TRIP_RNG_SWT",
        value = 612373248,
        possibleValues = mapOf(
            "TRIP_FUNC_TRIP_RNG_SWT_100KM" to 612373250,
            "TRIP_FUNC_TRIP_RNG_SWT_50KM" to 612373249,
        )
    ),
    PropertyData(
        alias = "ITripData.TRIP_INFO_TYPE_AVG_CONSUMPTION_ARRAY",
        type = 2,
        key = "TRIP_INFO_TYPE_AVG_CONSUMPTION_ARRAY",
        value = 20480,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVirtualSensor.TYPE_AVG_FUEL_CONSUMPTION",
        type = 3,
        key = "TYPE_AVG_FUEL_CONSUMPTION",
        value = 4194560,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVirtualSensor.TYPE_AVG_FUEL_CONSUMPTION_ONE_IGNITION",
        type = 3,
        key = "TYPE_AVG_FUEL_CONSUMPTION_ONE_IGNITION",
        value = 4195072,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVirtualSensor.TYPE_EV_BATTERY_PERCENTAGE",
        type = 3,
        key = "TYPE_EV_BATTERY_PERCENTAGE",
        value = 4210688,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVirtualSensor.TYPE_FUEL_PERCENTAGE",
        type = 3,
        key = "TYPE_FUEL_PERCENTAGE",
        value = 4211968,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVirtualSensor.TYPE_JOY_LIMIT_STATE",
        type = 3,
        key = "TYPE_JOY_LIMIT_STATE",
        value = 4195840,
        possibleValues = mapOf(
            "JOY_LIMIT_STATE_OFF" to 4195841,
            "JOY_LIMIT_STATE_ON" to 4195842,
            "JOY_LIMIT_STATE_UNKNOWN" to -1,
        )
    ),
    PropertyData(
        alias = "IVirtualSensor.TYPE_MAINTENANCE_MILEAGE",
        type = 3,
        key = "TYPE_MAINTENANCE_MILEAGE",
        value = 4206592,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVirtualSensor.TYPE_MAINTENANCE_MILEAGE_EV_SINCE_LAST",
        type = 3,
        key = "TYPE_MAINTENANCE_MILEAGE_EV_SINCE_LAST",
        value = 4215808,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVirtualSensor.TYPE_MAINTENANCE_MILEAGE_PURE_OIL_SINCE_LAST",
        type = 3,
        key = "TYPE_MAINTENANCE_MILEAGE_PURE_OIL_SINCE_LAST",
        value = 4216064,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVirtualSensor.TYPE_MAINTENANCE_MILEAGE_REMIND",
        type = 3,
        key = "TYPE_MAINTENANCE_MILEAGE_REMIND",
        value = 4207104,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVirtualSensor.TYPE_MAINTENANCE_MILEAGE_SINCE_LAST_ALL",
        type = 3,
        key = "TYPE_MAINTENANCE_MILEAGE_SINCE_LAST_ALL",
        value = 4215552,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVirtualSensor.TYPE_MAINTENANCE_TIME",
        type = 3,
        key = "TYPE_MAINTENANCE_TIME",
        value = 4206848,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVirtualSensor.TYPE_OIL_HEALTH",
        type = 3,
        key = "TYPE_OIL_HEALTH",
        value = 4216576,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVirtualSensor.TYPE_OIL_NUMBER_OF_DAY",
        type = 3,
        key = "TYPE_OIL_NUMBER_OF_DAY",
        value = 4216320,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVirtualSensor.TYPE_POTENTIAL_ENDURANCE_MILEAGE",
        type = 3,
        key = "TYPE_POTENTIAL_ENDURANCE_MILEAGE",
        value = 4211456,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVoice.VOICE_FUNC_ANNOUNCEMENTS_FOR_NOA",
        type = 2,
        key = "VOICE_FUNC_ANNOUNCEMENTS_FOR_NOA",
        value = -2130574336,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVoice.VOICE_FUNC_ANNOUNCEMENTS_FOR_NOA_START",
        type = 2,
        key = "VOICE_FUNC_ANNOUNCEMENTS_FOR_NOA_START",
        value = -2130574080,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_DRVR_SOD_REQ_CHG",
        type = 2,
        key = "VOICE_FUNC_DRVR_SOD_REQ_CHG",
        value = -2130639872,
        possibleValues = mapOf(
            "DRVR_SOD_REQ_CHG_LEFT_LAN" to -2130639871,
            "DRVR_SOD_REQ_CHG_NO" to 254,
            "DRVR_SOD_REQ_CHG_RIGHT_LAN" to -2130639870,
        )
    ),
    PropertyData(
        alias = "IADAS.SETTING_FUNC_DRVR_SOD_REQ_PILOT",
        type = 2,
        key = "VOICE_FUNC_DRVR_SOD_REQ_PILOT",
        value = -2130639360,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IADAS.VOICE_FUNC_SOD_LANE_CHG_SWITCH",
        type = 2,
        key = "VOICE_FUNC_SOD_LANE_CHG_SWITCH",
        value = 671748608,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVoice.VOICE_FUNC_SOD_PILOT_CFIRM",
        type = 2,
        key = "VOICE_FUNC_SOD_PILOT_CFIRM",
        value = 805373696,
        possibleValues = mapOf(
            "SOD_PILOT_CFIRM_ACTIVE" to 805373697,
            "SOD_PILOT_CFIRM_ACTIVE_NO_CMD" to 805373699,
            "SOD_PILOT_CFIRM_CAN_NOT_ACTIVE" to 805373698,
        )
    ),
    PropertyData(
        alias = "IWpc.WPC_FUNC_CHARGE_FORGET_REMINDER",
        type = 2,
        key = "WPC_FUNC_CHARGE_FORGET_REMINDER",
        value = 637731072,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IWpc.WPC_FUNC_CHARGE_STATES",
        type = 2,
        key = "WPC_FUNC_CHARGE_STATES",
        value = 637665536,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IWpc.WPC_FUNC_WORK_MODE",
        type = 2,
        key = "WPC_FUNC_WORK_MODE",
        value = 637600000,
        possibleValues = mapOf(
            "WORK_MODE_AUTO" to 637600001,
            "WORK_MODE_OFF" to 0,
        )
    ),
    PropertyData(
        alias = "IVehicle.SETTING_DHU_FAST_START_MODE",
        type = 2,
        key = "SETTING_DHU_FAST_START_MODE",
        value = 539512832,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_ABNORMAL_VEHICLE_ALARM",
        type = 2,
        key = "SETTING_FUNC_ABNORMAL_VEHICLE_ALARM",
        value = 539492608,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_ABNORMAL_VEHICLE_ALARM_MODE",
        type = 2,
        key = "SETTING_FUNC_ABNORMAL_VEHICLE_ALARM_MODE",
        value = 539497984,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_ARTIFICIAL_SOUND_PREVIEW",
        type = 2,
        key = "SETTING_FUNC_ARTIFICIAL_SOUND_PREVIEW",
        value = 539428608,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_ARTIFICIAL_SOUND_SWITCH",
        type = 2,
        key = "SETTING_FUNC_ARTIFICIAL_SOUND_SWITCH",
        value = 538575616,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_AVAS_SOUND_TYPE",
        type = 2,
        key = "SETTING_FUNC_AVAS_SOUND_TYPE",
        value = 538576640,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_AVAS_SWITCH",
        type = 2,
        key = "SETTING_FUNC_AVAS_SWITCH",
        value = 538576128,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_AVAS_VOLUME",
        type = 2,
        key = "SETTING_FUNC_AVAS_VOLUME",
        value = 538576384,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_BLIND_CAMERA_SYNC_RT_TURN",
        type = 2,
        key = "SETTING_FUNC_BLIND_CAMERA_SYNC_RT_TURN",
        value = 538772480,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_CDC_MODE",
        type = 2,
        key = "SETTING_FUNC_CDC_MODE",
        value = 540285440,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_CDC_MODE_WARNING",
        type = 2,
        key = "SETTING_FUNC_CDC_MODE_WARNING",
        value = 540285696,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_CONGESTION_AHEAD_ALARM",
        type = 2,
        key = "SETTING_FUNC_CONGESTION_AHEAD_ALARM",
        value = 539493376,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_CSD_POSITION",
        type = 2,
        key = "SETTING_FUNC_CSD_POSITION",
        value = 539504640,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_DANGEROUS_ROAD_ALARM",
        type = 2,
        key = "SETTING_FUNC_DANGEROUS_ROAD_ALARM",
        value = 539492864,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_DANGEROUS_ROAD_ALARM_MODE",
        type = 2,
        key = "SETTING_FUNC_DANGEROUS_ROAD_ALARM_MODE",
        value = 539498240,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_DAYLIGHT_SAVING_TIME",
        type = 2,
        key = "SETTING_FUNC_DAYLIGHT_SAVING_TIME",
        value = 538640896,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_DIGITAL_KEY",
        type = 2,
        key = "SETTING_FUNC_DIGITAL_KEY",
        value = 539496448,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_DIGITAL_KEY_REQ_STS",
        type = 2,
        key = "SETTING_FUNC_DIGITAL_KEY_REQ_STS",
        value = 539496704,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_DIGITAL_KEY_SUSPENSION",
        type = 2,
        key = "SETTING_FUNC_DIGITAL_KEY_SUSPENSION",
        value = 539497472,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_DIGITAL_KEY_TERMINATION",
        type = 2,
        key = "SETTING_FUNC_DIGITAL_KEY_TERMINATION",
        value = 539497216,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_DIGITAL_KEY_UNPAIR",
        type = 2,
        key = "SETTING_FUNC_DIGITAL_KEY_UNPAIR",
        value = 539496960,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_DIM_HOLIDAY_WALLPAPER",
        type = 2,
        key = "SETTING_FUNC_DIM_HOLIDAY_WALLPAPER",
        value = 538904320,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_DRIVER_ALERT_CONTROL",
        type = 2,
        key = "SETTING_FUNC_DRIVER_ALERT_CONTROL",
        value = 537002496,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_DRIVER_MODE_SOUND_SWITCH",
        type = 2,
        key = "SETTING_FUNC_DRIVER_MODE_SOUND_SWITCH",
        value = 539429888,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_EMERGENCY_VEHICLE_ALARM",
        type = 2,
        key = "SETTING_FUNC_EMERGENCY_VEHICLE_ALARM",
        value = 539493120,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_EMERGENCY_VEHICLE_ALARM_MODE",
        type = 2,
        key = "SETTING_FUNC_EMERGENCY_VEHICLE_ALARM_MODE",
        value = 539498496,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_ENERGY_PREDICTION_SWITCH",
        type = 2,
        key = "SETTING_FUNC_ENERGY_PREDICTION_SWITCH",
        value = 538903808,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_ENGINE_MAINTENANCE_TIME_RESET",
        type = 2,
        key = "SETTING_FUNC_ENGINE_MAINTENANCE_TIME_RESET",
        value = 539430656,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_ENTER_AUTO_SHOW_MODE_RE",
        type = 2,
        key = "SETTING_FUNC_ENTER_AUTO_SHOW_MODE_RE",
        value = 540280320,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_EXTERNAL_ARTIFICIAL_SOUND_SWITCH",
        type = 2,
        key = "SETTING_FUNC_EXTERNAL_ARTIFICIAL_SOUND_SWITCH",
        value = 538577408,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_EYE_BALL_TRACK",
        type = 2,
        key = "SETTING_FUNC_EYE_BALL_TRACK",
        value = 539427584,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_E_PEDAL",
        type = 2,
        key = "SETTING_FUNC_E_PEDAL",
        value = 538444032,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_FACE_CAMERA_COVER",
        type = 2,
        key = "SETTING_FUNC_FACE_CAMERA_COVER",
        value = 540147968,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_FACE_RECOGNITION",
        type = 2,
        key = "SETTING_FUNC_FACE_RECOGNITION",
        value = 540279552,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_FRONT_WIPER_IDLE",
        type = 2,
        key = "SETTING_FUNC_FRONT_WIPER_IDLE",
        value = 537658112,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_HILL_START_ASSIST",
        type = 2,
        key = "SETTING_FUNC_HILL_START_ASSIST",
        value = 539429376,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_HOLOGRAPHIC_ACTIVATED",
        type = 2,
        key = "SETTING_FUNC_HOLOGRAPHIC_ACTIVATED",
        value = 539500032,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_HOLOGRAPHIC_BACKLIGHT_LEVEL",
        type = 2,
        key = "SETTING_FUNC_HOLOGRAPHIC_BACKLIGHT_LEVEL",
        value = 539500544,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_HOLOGRAPHIC_BACKLIGHT_MODE",
        type = 2,
        key = "SETTING_FUNC_HOLOGRAPHIC_BACKLIGHT_MODE",
        value = 539500288,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_JOURNAL_LOGS",
        type = 2,
        key = "SETTING_FUNC_JOURNAL_LOGS",
        value = 538313472,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_LAUNCH_MODE_INDCN",
        type = 2,
        key = "SETTING_FUNC_LAUNCH_MODE_INDCN",
        value = 539429120,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_LOCK_REAR_SEAT_DISPLAY",
        type = 2,
        key = "SETTING_FUNC_LOCK_REAR_SEAT_DISPLAY",
        value = 538706176,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_MAINTENANCE_TIME_RESET",
        type = 2,
        key = "SETTING_FUNC_MAINTENANCE_TIME_RESET",
        value = 538968576,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_MIRROR_DIMMING",
        type = 2,
        key = "SETTING_FUNC_MIRROR_DIMMING",
        value = 537460992,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_MIRROR_DIPPING_SWITCH",
        type = 2,
        key = "SETTING_FUNC_MIRROR_DIPPING_SWITCH",
        value = 537461760,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_MULTI_FUNC_KNOB_DIRECTION",
        type = 2,
        key = "SETTING_FUNC_MULTI_FUNC_KNOB_DIRECTION",
        value = 540285952,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_MULTI_FUNC_KNOB_PRESS_STATUS",
        type = 2,
        key = "SETTING_FUNC_MULTI_FUNC_KNOB_PRESS_STATUS",
        value = 540286464,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_MULTI_FUNC_KNOB_ROTATE_STEP",
        type = 2,
        key = "SETTING_FUNC_MULTI_FUNC_KNOB_ROTATE_STEP",
        value = 540286208,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_PARK_COMFORT_MODE_OFF_REASON",
        type = 2,
        key = "SETTING_FUNC_PARK_COMFORT_MODE_OFF_REASON",
        value = 538838272,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_PASSENGER_AIRBAG",
        type = 2,
        key = "SETTING_FUNC_PASSENGER_AIRBAG",
        value = 539428096,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_PCM_TIMER",
        type = 2,
        key = "SETTING_FUNC_PCM_TIMER",
        value = 538640640,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_PRIVATE_LOCK_REMINDER",
        type = 2,
        key = "SETTING_FUNC_PRIVATE_LOCK_REMINDER",
        value = 537854464,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_REAR_MIRR_STREAM_SWITCH",
        type = 2,
        key = "SETTING_FUNC_REAR_MIRR_STREAM_SWITCH",
        value = 539100416,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_REAR_SPOILER_ADJUST",
        type = 2,
        key = "SETTING_FUNC_REAR_SPOILER_ADJUST",
        value = 538510336,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_REAR_SPOILER_POSN_REQUEST",
        type = 2,
        key = "SETTING_FUNC_REAR_SPOILER_POSN_REQUEST",
        value = 538510592,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_REAR_WINDOW_CLEAN",
        type = 2,
        key = "SETTING_FUNC_REAR_WINDOW_CLEAN",
        value = 537395712,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_RED_LIGHT_ALARM",
        type = 2,
        key = "SETTING_FUNC_RED_LIGHT_ALARM",
        value = 539493888,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_RED_LIGHT_ALARM_MODE",
        type = 2,
        key = "SETTING_FUNC_RED_LIGHT_ALARM_MODE",
        value = 539498752,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_RESET_SETTINGS_DEFAULT",
        type = 2,
        key = "SETTING_FUNC_RESET_SETTINGS_DEFAULT",
        value = 538181888,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_RMS_ACTIVE",
        type = 2,
        key = "SETTING_FUNC_RMS_ACTIVE",
        value = 538116352,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_SAY_HI",
        type = 2,
        key = "SETTING_FUNC_SAY_HI",
        value = 539427840,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_SCREEN_SAVER_CUSTOM_NAME",
        type = 2,
        key = "SETTING_FUNC_SCREEN_SAVER_CUSTOM_NAME",
        value = 539034624,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_SCREEN_SAVER_CUSTOM_PICTURE",
        type = 2,
        key = "SETTING_FUNC_SCREEN_SAVER_CUSTOM_PICTURE",
        value = 539035136,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_SCREEN_SAVER_CUSTOM_TEXT",
        type = 2,
        key = "SETTING_FUNC_SCREEN_SAVER_CUSTOM_TEXT",
        value = 539034880,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_SCREEN_SAVER_STYLE",
        type = 2,
        key = "SETTING_FUNC_SCREEN_SAVER_STYLE",
        value = 539034368,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_SPEED_CONTROL",
        type = 2,
        key = "SETTING_FUNC_SPEED_CONTROL",
        value = 537068032,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_SPEED_GUIDANCE_ALARM",
        type = 2,
        key = "SETTING_FUNC_SPEED_GUIDANCE_ALARM",
        value = 539493632,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_SPEED_LIMITATION",
        type = 2,
        key = "SETTING_FUNC_SPEED_LIMITATION",
        value = 537067776,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_STEERING_WHEEL_ANGLE_WARN_SWITCH",
        type = 2,
        key = "SETTING_FUNC_STEERING_WHEEL_ANGLE_WARN_SWITCH",
        value = 539430144,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_SUNROOF_TRANSPARENCY_AUTO",
        type = 2,
        key = "SETTING_FUNC_SUNROOF_TRANSPARENCY_AUTO",
        value = 537396992,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_TCAM_5G_SWITCH",
        type = 2,
        key = "SETTING_FUNC_TCAM_5G_SWITCH",
        value = 538314496,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_TEM_PROVISIONING_STATE",
        type = 2,
        key = "SETTING_FUNC_TEM_PROVISIONING_STATE",
        value = 538313984,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_TRACK_MODE",
        type = 2,
        key = "SETTING_FUNC_TRACK_MODE",
        value = 538904576,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_UNLOCK_P_GEAR",
        type = 2,
        key = "SETTING_FUNC_UNLOCK_P_GEAR",
        value = 539427072,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_VEHICLE_SAFETY_ALARM",
        type = 2,
        key = "SETTING_FUNC_VEHICLE_SAFETY_ALARM",
        value = 539494912,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_VEHICLE_SAFETY_ALARM_MODE",
        type = 2,
        key = "SETTING_FUNC_VEHICLE_SAFETY_ALARM_MODE",
        value = 539497728,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_VOICE_KEY_DISTANCE",
        type = 2,
        key = "SETTING_FUNC_VOICE_KEY_DISTANCE",
        value = 540283904,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_VOICE_POWER_MODE_SET",
        type = 2,
        key = "SETTING_FUNC_VOICE_POWER_MODE_SET",
        value = 540283648,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_VOICE_RECOGNITION",
        type = 2,
        key = "SETTING_FUNC_VOICE_RECOGNITION",
        value = 538706688,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_VOICE_SEARCH_KEY",
        type = 2,
        key = "SETTING_FUNC_VOICE_SEARCH_KEY",
        value = 540283392,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_VOLUME_LIMIT",
        type = 2,
        key = "SETTING_FUNC_VOLUME_LIMIT",
        value = 539495168,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_VOLUME_LIMIT_MAX",
        type = 2,
        key = "SETTING_FUNC_VOLUME_LIMIT_MAX",
        value = 539495424,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_VSTD_VIDEO_UPLOAD_STATUS",
        type = 2,
        key = "SETTING_FUNC_VSTD_VIDEO_UPLOAD_STATUS",
        value = 540285184,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_WAITING_MODE",
        type = 2,
        key = "SETTING_FUNC_WAITING_MODE",
        value = 539428352,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_WALLPAPER_SYNC",
        type = 2,
        key = "SETTING_FUNC_WALLPAPER_SYNC",
        value = 539033856,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_WALLPAPER_SYNC_STYLE",
        type = 2,
        key = "SETTING_FUNC_WALLPAPER_SYNC_STYLE",
        value = 539034112,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_WELCOME_SOUND",
        type = 2,
        key = "SETTING_FUNC_WELCOME_SOUND",
        value = 539099392,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_WELCOME_SOUND_TYPE",
        type = 2,
        key = "SETTING_FUNC_WELCOME_SOUND_TYPE",
        value = 539099648,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_WINDOW_PINCH_WARN",
        type = 2,
        key = "SETTING_FUNC_WINDOW_PINCH_WARN",
        value = 537396480,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.SETTING_FUNC_XCALL_KEY_LOCK",
        type = 2,
        key = "SETTING_FUNC_XCALL_KEY_LOCK",
        value = 538313216,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.VOICE_KEY_SEARCH_STATUS_COMPLETE",
        type = 2,
        key = "VOICE_KEY_SEARCH_STATUS_COMPLETE",
        value = 540283396,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.VOICE_KEY_SEARCH_STATUS_FAILED",
        type = 2,
        key = "VOICE_KEY_SEARCH_STATUS_FAILED",
        value = 540283395,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.VOICE_KEY_SEARCH_STATUS_IDLE",
        type = 2,
        key = "VOICE_KEY_SEARCH_STATUS_IDLE",
        value = 540283393,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IVehicle.VOICE_KEY_SEARCH_STATUS_IN_PROGRESS",
        type = 2,
        key = "VOICE_KEY_SEARCH_STATUS_IN_PROGRESS",
        value = 540283394,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AAC_LEVEL",
        type = 2,
        key = "HVAC_FUNC_AAC_LEVEL",
        value = 269747712,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_WIND_ELECDEFRS",
        type = 2,
        key = "HVAC_FUNC_WIND_ELECDEFRS",
        value = 269753088,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AI_CLIMATE_STATUS",
        type = 2,
        key = "HVAC_FUNC_AI_CLIMATE_STATUS",
        value = 269092096,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AQS_SWITCH",
        type = 2,
        key = "HVAC_FUNC_AQS_SWITCH",
        value = 268960256,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_AAC_SWITCH",
        type = 2,
        key = "HVAC_FUNC_AUTO_AAC_SWITCH",
        value = 269747456,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_BLOWING_MODE",
        type = 2,
        key = "HVAC_FUNC_AUTO_BLOWING_MODE",
        value = 268896000,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_CLOSE_WINDOW_REMIND_CONFIRM",
        type = 2,
        key = "HVAC_FUNC_AUTO_CLOSE_WINDOW_REMIND_CONFIRM",
        value = 269419264,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_DEHUMIDIFICATION",
        type = 2,
        key = "HVAC_FUNC_AUTO_DEHUMIDIFICATION",
        value = 268960512,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_DEHUMIDIFICATION_CONFIRM",
        type = 2,
        key = "HVAC_FUNC_AUTO_DEHUMIDIFICATION_CONFIRM",
        value = 269287936,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_DEHUMIDIFICATION_REQUEST",
        type = 2,
        key = "HVAC_FUNC_AUTO_DEHUMIDIFICATION_REQUEST",
        value = 269287680,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_SEAT_MASSAGE",
        type = 2,
        key = "HVAC_FUNC_AUTO_SEAT_MASSAGE",
        value = 268765184,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_SEAT_VENTILATION",
        type = 2,
        key = "HVAC_FUNC_AUTO_SEAT_VENTILATION",
        value = 268763904,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_AUTO_SECOND_ROW_CLIMATE",
        type = 2,
        key = "HVAC_FUNC_AUTO_SECOND_ROW_CLIMATE",
        value = 269484288,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_BLOWING_TEMP_COLOR",
        type = 2,
        key = "HVAC_FUNC_BLOWING_TEMP_COLOR",
        value = 268895744,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_CLIMATE_IS_SHOW",
        type = 2,
        key = "HVAC_FUNC_CLIMATE_IS_SHOW",
        value = 269748736,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_CLIMATISATION_ERROR_CONDITIONS",
        type = 2,
        key = "HVAC_FUNC_CLIMATISATION_ERROR_CONDITIONS",
        value = 269091584,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_CLOSE_AUTO_CONTROL_CONFIRM",
        type = 2,
        key = "HVAC_FUNC_CLOSE_AUTO_CONTROL_CONFIRM",
        value = 269749760,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_CLOSE_AUTO_CONTROL_REQUEST",
        type = 2,
        key = "HVAC_FUNC_CLOSE_AUTO_CONTROL_REQUEST",
        value = 269749504,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_CO2_HIGHER_REQUEST",
        type = 2,
        key = "HVAC_FUNC_CO2_HIGHER_REQUEST",
        value = 269353472,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_CO2_SWITCH",
        type = 2,
        key = "HVAC_FUNC_CO2_SWITCH",
        value = 269353216,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_HARDKEYPOP_AUTOOFF",
        type = 2,
        key = "HVAC_FUNC_HARDKEYPOP_AUTOOFF",
        value = 269754368,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_HARDKEYPOP_AUTOON",
        type = 2,
        key = "HVAC_FUNC_HARDKEYPOP_AUTOON",
        value = 269754112,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_HARDKEYPOP_FRONTDEFROSTOFF",
        type = 2,
        key = "HVAC_FUNC_HARDKEYPOP_FRONTDEFROSTOFF",
        value = 269754880,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_HARDKEYPOP_FRONTDEFROSTON",
        type = 2,
        key = "HVAC_FUNC_HARDKEYPOP_FRONTDEFROSTON",
        value = 269754624,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_REWIN_ELECDEFRST",
        type = 2,
        key = "HVAC_FUNC_REWIN_ELECDEFRST",
        value = 269753344,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_HARDKEYPOP_POWERON_AUTOOFF",
        type = 2,
        key = "HVAC_FUNC_HARDKEYPOP_POWERON_AUTOOFF",
        value = 269753856,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_SHOW_STEERWHL_A",
        type = 2,
        key = "HVAC_SHOW_STEERWHL_A",
        value = 269753600,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_HIDE_CLIMATE_APP",
        type = 2,
        key = "HVAC_FUNC_HIDE_CLIMATE_APP",
        value = 269748480,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_INTELLIGENT_AIR_POP",
        type = 2,
        key = "HVAC_FUNC_INTELLIGENT_AIR_POP",
        value = 269752320,
        possibleValues = mapOf(
            "INTELLIGENT_AIR_NO_POP" to 269752321,
            "INTELLIGENT_AIR_POP_1" to 269752322,
            "INTELLIGENT_AIR_POP_2" to 269752323
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_INTELLIGENT_AIR_POP_SELECT",
        type = 2,
        key = "HVAC_FUNC_INTELLIGENT_AIR_POP_SELECT",
        value = 269752832,
        possibleValues = mapOf(
            "INTELLIGENT_AIR_NO_POP" to 269752321,
            "INTELLIGENT_AIR_POP_1" to 269752322,
            "INTELLIGENT_AIR_POP_2" to 269752323
        )
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_INTELLIGENT_AIR_SWITCH",
        type = 2,
        key = "HVAC_FUNC_INTELLIGENT_AIR_SWITCH",
        value = 269752576,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_MODULE_CONNECT_STATUS",
        type = 2,
        key = "HVAC_FUNC_MODULE_CONNECT_STATUS",
        value = 269680896,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_PET_WINDOW_REMIND_REQUEST",
        type = 2,
        key = "HVAC_FUNC_PET_WINDOW_REMIND_REQUEST",
        value = 269747968,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_RAPID_COOLING",
        type = 2,
        key = "HVAC_FUNC_RAPID_COOLING",
        value = 269750016,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_RAPID_WARMING",
        type = 2,
        key = "HVAC_FUNC_RAPID_WARMING",
        value = 269750528,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_SUNROOF_POPUP",
        type = 2,
        key = "HVAC_FUNC_SUNROOF_POPUP",
        value = 269747200,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_SWEEPING_HORIZONTAL_POS",
        type = 2,
        key = "HVAC_FUNC_SWEEPING_HORIZONTAL_POS",
        value = 268895232,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IHvac.HVAC_FUNC_SWEEPING_VERTICAL_POS",
        type = 2,
        key = "HVAC_FUNC_SWEEPING_VERTICAL_POS",
        value = 268895488,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DM_FUNC_DIM_THEME_SET",
        type = 2,
        key = "DM_FUNC_DIM_THEME_SET",
        value = 570688000,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DM_FUNC_DIM_THEME_SYNC_DRIVEMODE",
        type = 2,
        key = "DM_FUNC_DIM_THEME_SYNC_DRIVEMODE",
        value = 570687744,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.DM_FUNC_ECO_BUTTON",
        type = 2,
        key = "DM_FUNC_ECO_BUTTON",
        value = 570556672,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IDriveMode.SETTING_FUNC_ESC_SWITCH_LEVEL",
        type = 2,
        key = "SETTING_FUNC_ESC_SWITCH_LEVEL",
        value = 570690816,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_APA_DETECT_PARKING_SPACE",
        type = 2,
        key = "PAS_FUNC_APA_DETECT_PARKING_SPACE",
        value = 588251904,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_APA_RPA_SWITCH",
        type = 2,
        key = "PAS_FUNC_APA_RPA_SWITCH",
        value = 587596288,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_AVP_ACTIVATED",
        type = 2,
        key = "PAS_FUNC_AVP_ACTIVATED",
        value = 588251392,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_ELE_MIRROR_SYS_ACTIVATED",
        type = 2,
        key = "PAS_FUNC_ELE_MIRROR_SYS_ACTIVATED",
        value = 588251648,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAC_3DVIEW_POSITION",
        type = 2,
        key = "PAS_FUNC_PAC_3DVIEW_POSITION",
        value = 587403776,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAC_APP_INIT_COMPLETED",
        type = 2,
        key = "PAS_FUNC_PAC_APP_INIT_COMPLETED",
        value = 587404544,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAC_AUTO_FRONT_ACTIV",
        type = 2,
        key = "PAS_FUNC_PAC_AUTO_FRONT_ACTIV",
        value = 587399936,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAC_AUTO_REVERSE_CAMERA",
        type = 2,
        key = "PAS_FUNC_PAC_AUTO_REVERSE_CAMERA",
        value = 587400192,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAC_CAMERA_TYPE",
        type = 2,
        key = "PAS_FUNC_PAC_CAMERA_TYPE",
        value = 587400448,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAC_NEARBY_OBJ_TRIGGER",
        type = 2,
        key = "PAS_FUNC_PAC_NEARBY_OBJ_TRIGGER",
        value = 587407872,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAC_OBSTACLE_DETECTION",
        type = 2,
        key = "PAS_FUNC_PAC_OBSTACLE_DETECTION",
        value = 587408128,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAC_OVERLAY_DSTINFO",
        type = 2,
        key = "PAS_FUNC_PAC_OVERLAY_DSTINFO",
        value = 587401728,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAC_OVERLAY_STEERPATH",
        type = 2,
        key = "PAS_FUNC_PAC_OVERLAY_STEERPATH",
        value = 587401216,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAC_OVERLAY_TOWBAR",
        type = 2,
        key = "PAS_FUNC_PAC_OVERLAY_TOWBAR",
        value = 587401472,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAC_STATUS",
        type = 2,
        key = "PAS_FUNC_PAC_STATUS",
        value = 587399425,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAC_SYS_AVA_STATUS",
        type = 2,
        key = "PAS_FUNC_PAC_SYS_AVA_STATUS",
        value = 587404032,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAC_TOP_VIEW_ZOOM_IN",
        type = 2,
        key = "PAS_FUNC_PAC_TOP_VIEW_ZOOM_IN",
        value = 587408384,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAC_TOURING_VIEW",
        type = 2,
        key = "PAS_FUNC_PAC_TOURING_VIEW",
        value = 587408640,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAC_VIEW_SELECTION",
        type = 2,
        key = "PAS_FUNC_PAC_VIEW_SELECTION",
        value = 587403520,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_RADAR_FRONT_CENTER",
        type = 2,
        key = "PAS_FUNC_PAS_RADAR_FRONT_CENTER",
        value = 587338240,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_RADAR_FRONT_INNER_LEFT",
        type = 2,
        key = "PAS_FUNC_PAS_RADAR_FRONT_INNER_LEFT",
        value = 587333888,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_RADAR_FRONT_INNER_RIGHT",
        type = 2,
        key = "PAS_FUNC_PAS_RADAR_FRONT_INNER_RIGHT",
        value = 587334144,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_RADAR_FRONT_LEFT_SIDE",
        type = 2,
        key = "PAS_FUNC_PAS_RADAR_FRONT_LEFT_SIDE",
        value = 587334912,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_RADAR_FRONT_OUT_LEFT",
        type = 2,
        key = "PAS_FUNC_PAS_RADAR_FRONT_OUT_LEFT",
        value = 587334400,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_RADAR_FRONT_OUT_RIGHT",
        type = 2,
        key = "PAS_FUNC_PAS_RADAR_FRONT_OUT_RIGHT",
        value = 587334656,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_RADAR_FRONT_RIGHT_SIDE",
        type = 2,
        key = "PAS_FUNC_PAS_RADAR_FRONT_RIGHT_SIDE",
        value = 587335168,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_RADAR_MAX_DISTANCE",
        type = 2,
        key = "PAS_FUNC_PAS_RADAR_MAX_DISTANCE",
        value = 587336960,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_RADAR_MIN_DISTANCE",
        type = 2,
        key = "PAS_FUNC_PAS_RADAR_MIN_DISTANCE",
        value = 587337216,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_RADAR_REAR_CENTER",
        type = 2,
        key = "PAS_FUNC_PAS_RADAR_REAR_CENTER",
        value = 587338496,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_RADAR_REAR_INNER_LEFT",
        type = 2,
        key = "PAS_FUNC_PAS_RADAR_REAR_INNER_LEFT",
        value = 587336448,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_RADAR_REAR_INNER_RIGHT",
        type = 2,
        key = "PAS_FUNC_PAS_RADAR_REAR_INNER_RIGHT",
        value = 587336704,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_RADAR_REAR_LEFT_SIDE",
        type = 2,
        key = "PAS_FUNC_PAS_RADAR_REAR_LEFT_SIDE",
        value = 587335424,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_RADAR_REAR_OUT_LEFT",
        type = 2,
        key = "PAS_FUNC_PAS_RADAR_REAR_OUT_LEFT",
        value = 587335936,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_RADAR_REAR_OUT_RIGHT",
        type = 2,
        key = "PAS_FUNC_PAS_RADAR_REAR_OUT_RIGHT",
        value = 587336192,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_RADAR_REAR_RIGHT_SIDE",
        type = 2,
        key = "PAS_FUNC_PAS_RADAR_REAR_RIGHT_SIDE",
        value = 587335680,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_RADAR_WORK_MODE",
        type = 2,
        key = "PAS_FUNC_PAS_RADAR_WORK_MODE",
        value = 587337728,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_RADAR_WORK_STATUS",
        type = 2,
        key = "PAS_FUNC_PAS_RADAR_WORK_STATUS",
        value = 587337984,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_SHOW_GRAPHICS",
        type = 2,
        key = "PAS_FUNC_PAS_SHOW_GRAPHICS",
        value = 587269376,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_STATUS",
        type = 2,
        key = "PAS_FUNC_PAS_STATUS",
        value = 587268352,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_PAS_TOP_VIEW",
        type = 2,
        key = "PAS_FUNC_PAS_TOP_VIEW",
        value = 587269120,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_RCTA_ACTIVATION",
        type = 2,
        key = "PAS_FUNC_RCTA_ACTIVATION",
        value = 587530496,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_RCTA_LEFT_WARNING",
        type = 2,
        key = "PAS_FUNC_RCTA_LEFT_WARNING",
        value = 587530752,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_RCTA_RIGHT_WARNING",
        type = 2,
        key = "PAS_FUNC_RCTA_RIGHT_WARNING",
        value = 587531008,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_RCTA_SHOW_GRAPHICS",
        type = 2,
        key = "PAS_FUNC_RCTA_SHOW_GRAPHICS",
        value = 587531264,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_SAP_PARK_IN_NOTI",
        type = 2,
        key = "PAS_FUNC_SAP_PARK_IN_NOTI",
        value = 587469056,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_SAP_PARK_IN_RESUME",
        type = 2,
        key = "PAS_FUNC_SAP_PARK_IN_RESUME",
        value = 587465728,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_SAP_PARK_IN_TYPE",
        type = 2,
        key = "PAS_FUNC_SAP_PARK_IN_TYPE",
        value = 587465472,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_SAP_PARK_IN_TYPE_RECOMMEND",
        type = 2,
        key = "PAS_FUNC_SAP_PARK_IN_TYPE_RECOMMEND",
        value = 587466496,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_SAP_PARK_OUT_COMFIRM",
        type = 2,
        key = "PAS_FUNC_SAP_PARK_OUT_COMFIRM",
        value = 587465984,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_SAP_PARK_OUT_NOTI",
        type = 2,
        key = "PAS_FUNC_SAP_PARK_OUT_NOTI",
        value = 587469312,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_SAP_PARK_TYPE",
        type = 2,
        key = "PAS_FUNC_SAP_PARK_TYPE",
        value = 587465216,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IPAS.PAS_FUNC_SAP_PROGRESS",
        type = 2,
        key = "PAS_FUNC_SAP_PROGRESS",
        value = 587466240,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_ALL_DOORS_ONE_KEY_SWITCH",
        type = 2,
        key = "BCM_FUNC_ALL_DOORS_ONE_KEY_SWITCH",
        value = 554763520,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_AUTO_CLOSE_DOOR_BY_SPEED_SWITCH",
        type = 2,
        key = "BCM_FUNC_AUTO_CLOSE_DOOR_BY_SPEED_SWITCH",
        value = 554763264,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_CSD_CONTROL_CUTOFF_LOCK",
        type = 2,
        key = "BCM_FUNC_CSD_CONTROL_CUTOFF_LOCK",
        value = 553845249,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_DOOR_ANTI_PINCH",
        type = 2,
        key = "BCM_FUNC_DOOR_ANTI_PINCH",
        value = 553785600,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_DOOR_CONTROL",
        type = 2,
        key = "BCM_FUNC_DOOR_CONTROL",
        value = 553783296,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_DOOR_OBSTACLE_DETECTED",
        type = 2,
        key = "BCM_FUNC_DOOR_OBSTACLE_DETECTED",
        value = 553785344,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_DOOR_POS",
        type = 2,
        key = "BCM_FUNC_DOOR_POS",
        value = 553779968,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_FOLD_REAR_MIRROR_DEFROST",
        type = 2,
        key = "BCM_FUNC_FOLD_REAR_MIRROR_DEFROST",
        value = 554763776,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_LIGHT_ALL_WEATHER_LIGHT",
        type = 2,
        key = "BCM_FUNC_LIGHT_ALL_WEATHER_LIGHT",
        value = 553981440,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_LIGHT_CORNERING_LAMPS",
        type = 2,
        key = "BCM_FUNC_LIGHT_CORNERING_LAMPS",
        value = 553977344,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_LIGHT_DAYTIME_RUNNING_LAMPS",
        type = 2,
        key = "BCM_FUNC_LIGHT_DAYTIME_RUNNING_LAMPS",
        value = 553978112,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_LIGHT_DIM_DIP_LAMPS",
        type = 2,
        key = "BCM_FUNC_LIGHT_DIM_DIP_LAMPS",
        value = 553978368,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_LIGHT_DIPPED_BEAM",
        type = 2,
        key = "BCM_FUNC_LIGHT_DIPPED_BEAM",
        value = 553976064,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_LIGHT_DRIVING_LAMPS",
        type = 2,
        key = "BCM_FUNC_LIGHT_DRIVING_LAMPS",
        value = 553976576,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_LIGHT_FRONT_POSITION_LAMPS",
        type = 2,
        key = "BCM_FUNC_LIGHT_FRONT_POSITION_LAMPS",
        value = 553977856,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_LIGHT_GRILLE_LAMP",
        type = 2,
        key = "BCM_FUNC_LIGHT_GRILLE_LAMP",
        value = 553981184,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_LIGHT_MAIN_BEAM",
        type = 2,
        key = "BCM_FUNC_LIGHT_MAIN_BEAM",
        value = 553976320,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_LIGHT_NUMBER_PLATE_LIGHT",
        type = 2,
        key = "BCM_FUNC_LIGHT_NUMBER_PLATE_LIGHT",
        value = 553981696,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_LIGHT_REAR_LOGO_LIGHT",
        type = 2,
        key = "BCM_FUNC_LIGHT_REAR_LOGO_LIGHT",
        value = 553980928,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_LIGHT_REAR_POSITION_LAMPS",
        type = 2,
        key = "BCM_FUNC_LIGHT_REAR_POSITION_LAMPS",
        value = 553978880,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_LIGHT_REVERSING_LAMPS",
        type = 2,
        key = "BCM_FUNC_LIGHT_REVERSING_LAMPS",
        value = 553979392,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_LIGHT_SIDE_MARKER_LIGHTS",
        type = 2,
        key = "BCM_FUNC_LIGHT_SIDE_MARKER_LIGHTS",
        value = 553978624,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_LIGHT_SPOT_LIGHTS",
        type = 2,
        key = "BCM_FUNC_LIGHT_SPOT_LIGHTS",
        value = 553977600,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_LIGHT_STOP_LAMPS",
        type = 2,
        key = "BCM_FUNC_LIGHT_STOP_LAMPS",
        value = 553979136,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_SCREEN_SAVER_POWER_KEY_PRESS",
        type = 2,
        key = "BCM_FUNC_SCREEN_SAVER_POWER_KEY_PRESS",
        value = 555747072,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_WINDOW_LOCK",
        type = 2,
        key = "BCM_FUNC_WINDOW_LOCK",
        value = 553845248,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_WINDOW_SYNC_SWITCH",
        type = 2,
        key = "BCM_FUNC_WINDOW_SYNC_SWITCH",
        value = 553846017,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IBcm.BCM_FUNC_WINDOW_TRANSPARENCY",
        type = 2,
        key = "BCM_FUNC_WINDOW_TRANSPARENCY",
        value = 553846016,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "IUnits.FUNC_UNIT_WARN_SPEED",
        type = 2,
        key = "FUNC_UNIT_WARN_SPEED",
        value = 620888832,
        possibleValues = mapOf(
            "UNIT_WARN_SPEED_KM_H" to 620888833,
            "UNIT_WARN_SPEED_MPH" to 620888834
        )
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_ABS",
        type = 3,
        key = "SENSOR_TYPE_ABS",
        value = 2101504,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_AIRBAG_STATUS_DRIVER",
        type = 3,
        key = "SENSOR_TYPE_AIRBAG_STATUS_DRIVER",
        value = 2109696,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_AIRBAG_STATUS_PASSENGER",
        type = 3,
        key = "SENSOR_TYPE_AIRBAG_STATUS_PASSENGER",
        value = 2109952,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_AQI_AMBIENT",
        type = 3,
        key = "SENSOR_TYPE_AQI_AMBIENT",
        value = 1049600,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_AQI_BACK_ROW",
        type = 3,
        key = "SENSOR_TYPE_AQI_BACK_ROW",
        value = 1049872,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_AQI_INDOOR",
        type = 3,
        key = "SENSOR_TYPE_AQI_INDOOR",
        value = 1049856,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_AQI_LEVEL_BACK_ROW",
        type = 3,
        key = "SENSOR_TYPE_AQI_LEVEL_BACK_ROW",
        value = 2106384,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_AQI_LEVEL_INDOOR",
        type = 3,
        key = "SENSOR_TYPE_AQI_LEVEL_INDOOR",
        value = 2106368,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_BATTERY_CURRENT",
        type = 3,
        key = "SENSOR_TYPE_BATTERY_CURRENT",
        value = 1051168,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_BRAKE_PRESSURE",
        type = 3,
        key = "SENSOR_TYPE_BRAKE_PRESSURE",
        value = 1053456,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_CAR_SPEED_ACCELERATION",
        type = 3,
        key = "SENSOR_TYPE_CAR_SPEED_ACCELERATION",
        value = 1054464,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_CO2_INDOOR",
        type = 3,
        key = "SENSOR_TYPE_CO2_INDOOR",
        value = 1051904,
        description = "Уровень CO₂ в салоне.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_CO2_LEVEL_INDOOR",
        type = 3,
        key = "SENSOR_TYPE_CO2_LEVEL_INDOOR",
        value = 2106624,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_ENGINE_START_STOP_STATE",
        type = 3,
        key = "SENSOR_TYPE_ENGINE_START_STOP_STATE",
        value = 2103040,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_EV_BATTERY_STATE",
        type = 3,
        key = "SENSOR_TYPE_EV_BATTERY_STATE",
        value = 2102528,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_EV_BATTERY_TEMPERATURE",
        type = 3,
        key = "SENSOR_TYPE_EV_BATTERY_TEMPERATURE",
        value = 1051152,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_EYE_BALL_TRACK_STATE",
        type = 3,
        key = "SENSOR_TYPE_EYE_BALL_TRACK_STATE",
        value = 3148800,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_HANDBRAKE_STATE",
        type = 3,
        key = "SENSOR_TYPE_HANDBRAKE_STATE",
        value = 2097920,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_HVSYSRLY_STS",
        type = 3,
        key = "SENSOR_TYPE_HVSYSRLY_STS",
        value = 3150080,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_LANE_DEPARTURE",
        type = 3,
        key = "SENSOR_TYPE_LANE_DEPARTURE",
        value = 3149056,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_MOTO_SPEED_FRONT",
        type = 3,
        key = "SENSOR_TYPE_MOTO_SPEED_FRONT",
        value = 1057024,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_MOTO_SPEED_REAR",
        type = 3,
        key = "SENSOR_TYPE_MOTO_SPEED_REAR",
        value = 1057280,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_MOTO_TORQUE_FRONT",
        type = 3,
        key = "SENSOR_TYPE_MOTO_TORQUE_FRONT",
        value = 1056512,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_MOTO_TORQUE_REAR",
        type = 3,
        key = "SENSOR_TYPE_MOTO_TORQUE_REAR",
        value = 1056768,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_PM25_AMBIENT",
        type = 3,
        key = "SENSOR_TYPE_PM25_AMBIENT",
        value = 1049088,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_PM25_BACK_ROW",
        type = 3,
        key = "SENSOR_TYPE_PM25_BACK_ROW",
        value = 1049360,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_PM25_INDOOR",
        type = 3,
        key = "SENSOR_TYPE_PM25_INDOOR",
        value = 1049344,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_PM25_LEVEL_AMBIENT",
        type = 3,
        key = "SENSOR_TYPE_PM25_LEVEL_AMBIENT",
        value = 2105600,
        description = "Уровень PM2.5 снаружи.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_PM25_LEVEL_BACK_ROW",
        type = 3,
        key = "SENSOR_TYPE_PM25_LEVEL_BACK_ROW",
        value = 2105872,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_PM25_STATE_AMBIENT",
        type = 3,
        key = "SENSOR_TYPE_PM25_STATE_AMBIENT",
        value = 2106880,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_PM25_STATE_BACK_ROW",
        type = 3,
        key = "SENSOR_TYPE_PM25_STATE_BACK_ROW",
        value = 2107152,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_PM25_STATE_INDOOR",
        type = 3,
        key = "SENSOR_TYPE_PM25_STATE_INDOOR",
        value = 2107136,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_RAIN_SENSOR_STATE",
        type = 3,
        key = "SENSOR_TYPE_RAIN_SENSOR_STATE",
        value = 3149568,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_REAR_WHEEL_ANGEL",
        type = 3,
        key = "SENSOR_TYPE_REAR_WHEEL_ANGEL",
        value = 1057536,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_ROLLBAR_POSITION_FRONT",
        type = 3,
        key = "SENSOR_TYPE_ROLLBAR_POSITION_FRONT",
        value = 1055488,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_ROLLBAR_POSITION_REAR",
        type = 3,
        key = "SENSOR_TYPE_ROLLBAR_POSITION_REAR",
        value = 1055744,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_ROLLBAR_TORQUE_FRONT",
        type = 3,
        key = "SENSOR_TYPE_ROLLBAR_TORQUE_FRONT",
        value = 1056000,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_ROLLBAR_TORQUE_REAR",
        type = 3,
        key = "SENSOR_TYPE_ROLLBAR_TORQUE_REAR",
        value = 1056256,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_RPM",
        type = 3,
        key = "SENSOR_TYPE_RPM",
        value = 1050880,
        description = "Обороты двигателя (RPM), об/мин.",
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_SAFE_BELT_DRIVER_ORIGIN_STATUS",
        type = 3,
        key = "SENSOR_TYPE_SAFE_BELT_DRIVER_ORIGIN_STATUS",
        value = 2167296,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_SAFE_BELT_PASSENGER",
        type = 3,
        key = "SENSOR_TYPE_SAFE_BELT_PASSENGER",
        value = 2102016,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_SAFE_BELT_ROW2_CENTER",
        type = 3,
        key = "SENSOR_TYPE_SAFE_BELT_ROW2_CENTER",
        value = 2103808,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_SAFE_BELT_ROW2_LEFT",
        type = 3,
        key = "SENSOR_TYPE_SAFE_BELT_ROW2_LEFT",
        value = 2103296,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_SAFE_BELT_ROW2_RIGHT",
        type = 3,
        key = "SENSOR_TYPE_SAFE_BELT_ROW2_RIGHT",
        value = 2103552,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_SEAT_PRESSURE_DRIVER",
        type = 3,
        key = "SENSOR_TYPE_SEAT_PRESSURE_DRIVER",
        value = 1053952,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_SEAT_PRESSURE_PASSENGER",
        type = 3,
        key = "SENSOR_TYPE_SEAT_PRESSURE_PASSENGER",
        value = 1054208,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_STATUS_TWLIBRISTS",
        type = 3,
        key = "SENSOR_TYPE_STATUS_TWLIBRISTS",
        value = 2101008,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_STEERING_WHEEL_ANGLE_SPEED",
        type = 3,
        key = "SENSOR_TYPE_STEERING_WHEEL_ANGLE_SPEED",
        value = 1052928,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_TOO_CLOSE_TO_FRONT_CAR",
        type = 3,
        key = "SENSOR_TYPE_TOO_CLOSE_TO_FRONT_CAR",
        value = 3149312,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_VEHICLE_WEIGHT",
        type = 3,
        key = "SENSOR_TYPE_VEHICLE_WEIGHT",
        value = 1053184,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_WARN_STEERING_ASSISTANCE_FAULT",
        type = 3,
        key = "SENSOR_TYPE_WARN_STEERING_ASSISTANCE_FAULT",
        value = 3147520,
        possibleValues = emptyMap()
    ),
    PropertyData(
        alias = "ISensor.SENSOR_TYPE_WARN_TRANSMISSION_SYSTEM_FAULT",
        type = 3,
        key = "SENSOR_TYPE_WARN_TRANSMISSION_SYSTEM_FAULT",
        value = 3147264,
        possibleValues = emptyMap()
    )
)
