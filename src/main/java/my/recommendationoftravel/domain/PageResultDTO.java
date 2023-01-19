package my.recommendationoftravel.service;

import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO{

    private List<Integer> pageList;
    private int totalPage;
    private int page;
    private int size;
    private int start;
    private int end;
    private boolean prev;
    private boolean next;

    public PageResultDTO(Pageable pageable, int totalPage) {
        this.totalPage = (int)(Math.ceil(totalPage/10.0));
        makePageList(pageable);

    }

    private void makePageList(Pageable pageable) {
        this.page = pageable.getPageNumber() + 1;
        this.size = pageable.getPageSize();

        int tempEnd = (int)(Math.ceil(page/10.0)) * 10;
        start = tempEnd - 9;
        prev = start > 1;
        end = totalPage > tempEnd ? tempEnd : totalPage;
        next = totalPage > tempEnd;
        pageList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());
    }
}
