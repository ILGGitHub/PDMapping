package es.enxenio.ilga.pdmapping.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

@Component
public class RepositorioFicheiros implements ServletContextAware {

	private Logger log = LoggerFactory.getLogger(RepositorioFicheiros.class);
	private File base;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.base = new File(servletContext.getRealPath("/"));
	}

	public File getFicheiro(String ruta) {
		return new File(base, ruta);
	}

	public boolean existeFicheiro(File ficheiro, File directorio) {
		File f = new File(directorio.getPath() + File.separator
				+ ficheiro.getPath());
		return f.exists() && f.isFile();
	}

	public void servirFicheiro(File ficheiro, HttpServletResponse resposta,
			String contentType) {
		InputStream in = null;
		OutputStream out = null;

		resposta.setContentType(contentType);

		try {
			in = new FileInputStream(ficheiro);
			out = resposta.getOutputStream();

			IOUtils.copy(in, out);
			resposta.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();

				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}