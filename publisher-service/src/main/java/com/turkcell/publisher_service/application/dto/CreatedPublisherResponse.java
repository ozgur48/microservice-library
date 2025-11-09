package com.turkcell.publisher_service.application.dto;

import java.util.UUID;

public record CreatedPublisherResponse(UUID id, String name, String address) {
}
