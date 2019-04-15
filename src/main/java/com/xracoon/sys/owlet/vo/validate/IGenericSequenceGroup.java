/**
* @author yangtianxin
* @date 2016年6月8日
* Copyright 2016, .... All right reserved. 
*/

package com.xracoon.sys.owlet.vo.validate;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * 默认验证顺序接口
 */
@GroupSequence(value = { IExistGroup.class, IVaildGroup.class,
		Default.class })
public interface IGenericSequenceGroup {
}