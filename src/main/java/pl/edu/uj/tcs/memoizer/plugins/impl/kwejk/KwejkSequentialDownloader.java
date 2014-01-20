package pl.edu.uj.tcs.memoizer.plugins.impl.kwejk;

import java.net.URI;

import pl.edu.uj.tcs.memoizer.plugins.EViewType;
import pl.edu.uj.tcs.memoizer.plugins.IPluginFactory;
import pl.edu.uj.tcs.memoizer.plugins.common.CommonMemeDownloader;
import pl.edu.uj.tcs.memoizer.plugins.common.CommonSequentialDownloader;
import pl.edu.uj.tcs.memoizer.serialization.IStateObject;

public class KwejkSequentialDownloader extends CommonSequentialDownloader{

	public KwejkSequentialDownloader(String serviceName, IStateObject state,
			EViewType view, URI workingUrl, IPluginFactory pluginFactory,
			CommonMemeDownloader memeDownloader) {
		super(serviceName, state, view, workingUrl, pluginFactory, memeDownloader);
	}

}
