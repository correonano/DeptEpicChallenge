package com.dept.deptepicchallenge.data.model

object EpicDetailMapper {
    fun toDomain(model: EpicDetailLocal): EpicDetail {
        return EpicDetail(
            model.identifier,
            model.caption,
            model.image,
            model.versionImage,
            model.date,
            Coordinates(model.lat, model.lon)
        )
    }

    fun toDomainList(model: List<EpicDetailLocal>): List<EpicDetail> {
        return model.map {
            toDomain(it)
        }
    }

    fun fromDomain(model: EpicDetail): EpicDetailLocal {
        return EpicDetailLocal(
            model.identifier,
            model.caption,
            model.image,
            model.version,
            model.date,
            model.date.split(" ").get(0),
            model.coordinates.lat,
            model.coordinates.lon
        )
    }

    fun fromDomainList(model: List<EpicDetail>): List<EpicDetailLocal> {
        return model.map {
            fromDomain(it)
        }
    }
}