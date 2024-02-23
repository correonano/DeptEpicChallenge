package com.dept.deptepicchallenge.data.model

object DateMapper {
    fun toDomain(model: DateLocal): DateDto {
        return DateDto(
            model.date,
        )
    }

    fun toDomainList(model: List<DateLocal>): List<DateDto> {
        return model.map {
            toDomain(it)
        }
    }

    fun fromDomain(model: DateDto): DateLocal {
        return DateLocal(
            model.date,
        )
    }

    fun fromDomainList(model: List<DateDto>): List<DateLocal> {
        return model.map {
            fromDomain(it)
        }
    }
}