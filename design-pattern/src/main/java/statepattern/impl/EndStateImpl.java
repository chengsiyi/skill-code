package statepattern.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import statepattern.FileSub;
import statepattern.State;

/**
 * @author chengsiyi
 * @date 2018/10/16 20:38
 */
public class EndStateImpl implements State {

    private static final Logger logger = LoggerFactory.getLogger(EndStateImpl.class);

    public void submit(FileSub fileSub){
        logger.info("end=====");
    }
}
