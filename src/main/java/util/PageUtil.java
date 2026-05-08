package util;

import com.example.edustream_studentMS.dto.responseDTO.PageResponseDTO;
import org.springframework.data.domain.Page;

public class PageUtil {

    private PageUtil() {} // prevent instantiation

    public static <T> PageResponseDTO<T> toPageResponse(Page<T> page) {
        return PageResponseDTO.<T>builder()
                .content(page.getContent())
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }
}