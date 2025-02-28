package com.book.shop.repository;

import com.book.shop.dto.BookSearchParametersDto;
import com.book.shop.models.Book;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private static final String TITLE = "title";
    private static final String AUTHOR = "author";
    private final SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParametersDto searchParameters) {
        Specification<Book> specification = Specification.where(null);
        specification = applyFilter(specification, searchParameters.titles(), TITLE);
        specification = applyFilter(specification, searchParameters.authors(), AUTHOR);
        return specification;
    }

    private Specification<Book> applyFilter(
            Specification<Book> specification, String[] values, String filterType
    ) {
        return Optional.ofNullable(values)
                .filter(arr -> arr.length > 0)
                .map(arr -> specification.and(
                        bookSpecificationProviderManager
                                .getSpecificationProvider(filterType)
                                .getSpecification(arr)))
                .orElse(specification);
    }
}
