package com.midas.nuevatienda.mapper;

public interface IMapper <I, O>{
    O map(I in);
}
