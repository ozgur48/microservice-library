package com.turkcell.memberservice.domain.exception;

import com.turkcell.memberservice.domain.model.MemberId;

import java.util.UUID;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException(UUID id){
        super("Member, ID: " + id + "could not find.!");
    }
    // uuid ve BookId value object'ini alır
    public MemberNotFoundException(MemberId memberId){
        super("Member, ID: " + memberId.value() + "could not find.!");
    }

}
