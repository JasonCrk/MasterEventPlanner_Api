package com.SAR.ReservationsSAR.context.establishment.domain.mappers;

import com.SAR.ReservationsSAR.context.establishment.domain.responses.EstablishmentDetailsResponse;
import com.SAR.ReservationsSAR.context.establishment.domain.responses.EstablishmentItemResponse;
import com.SAR.ReservationsSAR.context.establishment.domain.responses.EstablishmentSimpleResponse;
import com.SAR.ReservationsSAR.context.establishment.domain.Establishment;
import com.SAR.ReservationsSAR.context.topic.domain.mappers.TopicMapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", imports = {TopicMapper.class})
public interface EstablishmentMapper {

    EstablishmentMapper INSTANCE = Mappers.getMapper(EstablishmentMapper.class);

    @Mapping(expression = "java(establishment.getImages().get(0).getImageUrl())", target = "firstImage")
    EstablishmentSimpleResponse toSimple(Establishment establishment);

    @Mapping(expression = "java(Double.valueOf((double) establishment.getPricePerHour() / 100))", target = "pricePerHour")
    EstablishmentDetailsResponse toDetails(Establishment establishment);

    @Mapping(expression = "java(establishment.getImages().get(0).getImageUrl())", target = "firstImage")
    EstablishmentItemResponse toItem(Establishment establishment);

    List<EstablishmentItemResponse> toItemList(List<Establishment> establishment);
}
