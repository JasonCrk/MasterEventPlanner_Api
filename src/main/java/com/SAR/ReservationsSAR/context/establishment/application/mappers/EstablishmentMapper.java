package com.SAR.ReservationsSAR.context.establishment.application.mappers;

import com.SAR.ReservationsSAR.context.establishment.application.responses.EstablishmentDetailsResponse;
import com.SAR.ReservationsSAR.context.establishment.application.responses.EstablishmentItemResponse;
import com.SAR.ReservationsSAR.context.establishment.application.responses.EstablishmentSimpleResponse;
import com.SAR.ReservationsSAR.context.establishment.domain.Establishment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstablishmentMapper {

    EstablishmentMapper INSTANCE = Mappers.getMapper(EstablishmentMapper.class);

    @Mapping(expression = "java(establishment.getImages().get(0).getImageUrl())", target = "firstImage")
    EstablishmentSimpleResponse toSimple(Establishment establishment);

    EstablishmentDetailsResponse toDetails(Establishment establishment);

    @Mapping(expression = "java(establishment.getImages().get(0).getImageUrl())", target = "firstImage")
    EstablishmentItemResponse toItem(Establishment establishment);

    List<EstablishmentItemResponse> toItemList(List<Establishment> establishment);
}
