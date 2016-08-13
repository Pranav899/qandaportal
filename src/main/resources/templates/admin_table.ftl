<#list questions as question>
		<tr>
						<td class = "tableheadrows"></td>
						<td class = "tableheadrows">${question.questionId}</td>
						<td class = "tableheadrows">${question.question}</td>
						<td class = "tableheadrows"><input type = "button" value = "${question.parentQuestionId}" class = "buttons"></td>
						<td class = "tableheadrows"><form><input type = "button" data-question-Id = ${question.questionId} data-parent-Id = ${question.parentQuestionId} value = "confirm" class = "buttons confirm"></form></td>
						<td class = "tableheadrows"><form><input type = "button" data-question-Id = ${question.questionId} data-parent-Id = ${question.parentQuestionId} value = "No" class = "buttons deny"></form></td>
		</tr>
</#list>