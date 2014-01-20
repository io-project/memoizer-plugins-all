package pl.edu.uj.tcs.memoizer.plugins.impl.demoty2;

import java.net.URI;

import pl.edu.uj.tcs.memoizer.plugins.EViewType;
import pl.edu.uj.tcs.memoizer.plugins.IPluginFactory;
import pl.edu.uj.tcs.memoizer.plugins.common.CommonMemeDownloader;
import pl.edu.uj.tcs.memoizer.plugins.common.CommonSingleDownloader;
import pl.edu.uj.tcs.memoizer.serialization.IStateObject;

public class Demoty2SingleDownloader extends CommonSingleDownloader{

	public Demoty2SingleDownloader(String serviceName, IStateObject state,
			EViewType view, URI workingUrl, IPluginFactory pluginFactory,
			CommonMemeDownloader memeDownloader) {
		super(serviceName, state, view, workingUrl, pluginFactory, memeDownloader);
	}

}
