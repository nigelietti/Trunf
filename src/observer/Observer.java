package observer;

import Network.GenericMessage;


public interface Observer {
        void update(GenericMessage message);

}
