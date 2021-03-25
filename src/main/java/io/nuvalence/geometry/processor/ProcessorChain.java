package io.nuvalence.geometry.processor;

import io.nuvalence.geometry.dto.AnalyzeRectangleResponse;
import io.nuvalence.geometry.model.Shape;
import io.nuvalence.geometry.util.SortedPair;

import java.util.ArrayList;
import java.util.List;

public class ProcessorChain<T extends Shape> implements Processor<T> {
    private final List<Processor<T>> processors = new ArrayList<>();

    public void add(final Processor<T> processor)
    {
        processors.add(processor);
    }

    public void remove(final Processor<T> processor)
    {
        processors.remove(processor);
    }

    public boolean process(final SortedPair<T> pair, final AnalyzeRectangleResponse response)
    {
        for(final Processor<T> processor : processors)
        {
            if (!processor.process(pair, response))
            {
                return false;
            }
        }
        return true;
    }
}
