package com.appjangle.filesync.internal.engine

import com.appjangle.filesync.FileOperation
import com.appjangle.filesync.FileOperationContext
import com.appjangle.filesync.Metadata
import com.appjangle.filesync.internal.engine.metadata.MetadataUtilsJre
import de.mxro.file.FileItem
import java.util.List
import com.appjangle.filesync.internal.engine.metadata.MetadataImpl

class FileUtils {

	def hasMetadata(FileItem forFolder) {

		forFolder.assertFolder(".filesync-meta").getChild("nodes.xml").exists
	}

	def assertMetadata(FileItem forFolder) {
		val metadataFolder = forFolder.assertFolder(".filesync-meta")

		metadataFolder.visible = false;

		if (!metadataFolder.getChild("nodes.xml").exists) {
			val metadataFile = metadataFolder.createFile("nodes.xml")
			val metadata = new MetadataImpl
			MetadataUtilsJre.saveToFile(metadata, metadataFile)
			return metadata
		}

		MetadataUtilsJre.readFromFile(metadataFolder.getChild("nodes.xml"))
	}

	def execute(List<FileOperation> operations, FileItem folder, Metadata metadata) {

		val ctx = new FileOperationContext() {

			override folder() {
				folder
			}

			override metadata() {
				metadata
			}

		}

		for (op : operations) {
			op.apply(ctx)

		}

	}

	/**
     * based on http://grepcode.com/file_/repository.springsource.com/org.apache.activemq/com.springsource.org.apache.kahadb/5.3.0/org/apache/kahadb/util/IOHelper.java/?v=source
     * 
     * Converts any string into a string that is safe to use as a file name.
     * The result will only include ascii characters and numbers, and the "-","_", and "." characters.
     *
     * @param name
     * @param dirSeparators 
     * @param maxFileLength 
     * @return
     */
	def toFileSystemSafeName(String name, boolean dirSeparators, int maxFileLength) {
		val size = name.length();
		val StringBuffer rc = new StringBuffer(size * 2);
		for (i : 0 ..< size) {
			val char c = name.charAt(i);
			var boolean valid = c >= 'a' && c <= 'z';
			valid = valid || (c >= 'A' && c <= 'Z');
			valid = valid || (c >= '0' && c <= '9');
			valid = valid || (c == '_') || (c == '-') || (c == '.') || (c == '#') ||
				(dirSeparators && ( (c == '/') || (c == '\\')));

			if (valid) {
				rc.append(c);
			} else {
				rc.append("_")

			//rc.append('#');
			//rc.append(HexSupport.toHexFromInt(c, true));
			}
		}
		var result = rc.toString();
		if (result.length() > maxFileLength) {
			result = result.substring(result.length() - maxFileLength, result.length());
		}
		return result;
	}

}
