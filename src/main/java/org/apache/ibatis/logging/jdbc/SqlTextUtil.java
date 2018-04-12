/**
 *    Copyright ${license.git.copyrightYears} the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.logging.jdbc;

/**
 * @author Chi Seok Song
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자        수정내용
 *  -----------   ------------    ---------------------------
 *   2018-04-12       Chi Seok Song         최초 생성
 * </pre>
 */
public class SqlTextUtil {
    public static String leftTrim(String str) {
        if (str == null) {
            return str;
        }
        int s = 0;
        int e = 0;
        StringBuilder buf = new StringBuilder();

        String line = null;
        int cnt = 0;
        int iPos = 0;
        int cPos = 0;
        while ((e = str.indexOf('\n', s)) >= 0) {
            line = str.substring(s, e + 1);
            if (!line.trim().equals("")) {
                if (cnt == 0) {
                    iPos = getLeftPos(line);
                    buf.append(line.substring(iPos));
                } else {
                    cPos = getLeftPos(line);
                    if (cPos < iPos) {
                        buf.append(line.substring(cPos));
                    } else {
                        buf.append(line.substring(iPos));
                    }
                }
                cnt++;
            }
            s = e + 1;
        }
        line = str.substring(s);

        cPos = getLeftPos(line);
        if (cPos < iPos) {
            buf.append(line.substring(cPos));
        } else {
            buf.append(line.substring(iPos));
        }
        return buf.toString().trim();
    }

    private static int getLeftPos(String str) {
        if (str == null) {
            return 0;
        }
        char[] chs = str.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            char ch = chs[i];
            if ((ch != '\t') && (ch != ' ')) {
                return i;
            }
        }
        return 0;
    }
}
