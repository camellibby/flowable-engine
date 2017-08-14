/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flowable.cmmn.engine.impl.history;

import org.flowable.cmmn.engine.CmmnEngineConfiguration;
import org.flowable.cmmn.engine.impl.persistence.entity.HistoricMilestoneInstanceEntity;
import org.flowable.cmmn.engine.impl.persistence.entity.HistoricMilestoneInstanceEntityManager;
import org.flowable.cmmn.engine.runtime.MilestoneInstance;

/**
 * @author Joram Barrez
 */
public class DefaultCmmnHistoryManager implements CmmnHistoryManager {
    
    protected CmmnEngineConfiguration cmmnEngineConfiguration;
    
    public DefaultCmmnHistoryManager(CmmnEngineConfiguration cmmnEngineConfiguration) {
        this.cmmnEngineConfiguration = cmmnEngineConfiguration;
    }
    
    @Override
    public void recordMilestoneReached(MilestoneInstance milestoneInstance) {
        HistoricMilestoneInstanceEntityManager historicMilestoneInstanceEntityManager = cmmnEngineConfiguration.getHistoricMilestoneInstanceEntityManager();
        HistoricMilestoneInstanceEntity historicMilestoneInstanceEntity = historicMilestoneInstanceEntityManager.create();
        historicMilestoneInstanceEntity.setName(milestoneInstance.getName());
        historicMilestoneInstanceEntity.setCaseInstanceId(milestoneInstance.getCaseInstanceId());
        historicMilestoneInstanceEntity.setCaseDefinitionId(milestoneInstance.getCaseDefinitionId());
        historicMilestoneInstanceEntity.setElementId(milestoneInstance.getElementId());
        historicMilestoneInstanceEntity.setTimeStamp(cmmnEngineConfiguration.getClock().getCurrentTime());
        historicMilestoneInstanceEntityManager.insert(historicMilestoneInstanceEntity);
    }

}
